package org.acme.cognito;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



public class CognitoAuth{

    String accessKey = "ASIA4N3X3YDULTDCKUIK";
    String secretKey = "V7OrTm7jtxhoxHrgI4ge+SWgVuNAe0umKP0dCe+Y";
    String userPool = "us-east-1_1CgaMYMVR";
    String clientId = "d26mbcu8r643el8ao9cf2ge6q";

    String sessionToken= "FwoGZXIvYXdzEKr//////////wEaDNmJoiym8cGeUcuXdCLWAbSdN5AQQT8FXCvYb3qIeqk/tJTb9di0J1g+2HbRBOaZTpN4SMaX9BO9hJqlFOHxL8JXDBR77btfkpo9lu1yQQEgo2wj3uiPjo9B+EyAJ1IWzuLZSZsa078uqrh1V4YlwiyYZHEQdChNynpX+ZGHPTCPsn3Z+rFYo0sZY/NV4KD5aJW0TjTC+rN7tcdOk8f4qcaH6x0zuqcGJgrWXlwCr4rS7sv5v7i2ydFeclIfSERg3LEx9Agl3x+t8XmdNwUctZO3PmTHZ52GlQqN7HiK874ODPOXXCkoosmFogYyLdtnx5soHJ72HRvjCA81onymipLzZkZpPw4a/EibndXIHthRTjbYJPJd2GeLtA==";

    AWSCognitoIdentityProvider client = createCognitoClient();

    private AWSCognitoIdentityProvider createCognitoClient() {
        AWSSessionCredentials sessionCredentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(sessionCredentials);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public Map<String, String> login(String email, String password) {
        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("USERNAME", email);
            put("PASSWORD", password);
        }};

        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withClientId(clientId)
                .withAuthParameters(authParams);
        InitiateAuthResult authResult = client.initiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();
        return new LinkedHashMap<String, String>() {{
            put("idToken", resultType.getIdToken());
            put("accessToken", resultType.getAccessToken());
            put("refreshToken", resultType.getRefreshToken());
            put("message", "Successfully login");
        }};
    }

    public SignUpResult signUp(String name, String email, String password) {
        SignUpRequest request = new SignUpRequest().withClientId(clientId)
                .withUsername(email)
                .withPassword(password)
                .withUserAttributes(
                        new AttributeType()
                                .withName("name")
                                .withValue(name));
        SignUpResult result = client.signUp(request);
        return result;
    }

    public String hashPassword(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashBytes);
    }

}
