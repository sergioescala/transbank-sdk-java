package cl.transbank.onepay;

import lombok.Getter;
import lombok.ToString;

public abstract class Onepay {
    public static final String APP_KEY = "04533c31-fe7e-43ed-bbc4-1c8ab1538afp";
    public static final Channel DEFAULT_CHANNEL = Channel.WEB;
    public static final String DEFAULT_CALLBACK = "http://no.callback.has/been.set";
    private static volatile IntegrationType integrationType = IntegrationType.TEST;
    private static volatile String apiKey;
    private static volatile String sharedSecret;
    private static volatile String callbackUrl;
    private static volatile String appScheme;

    public static IntegrationType getIntegrationType() {
        return integrationType;
    }

    public static String getCurrentIntegrationTypeUrl() {
        return String.format(
                "%s/ewallet-plugin-api-services/services/transactionservice",
                getIntegrationType().getApiBase());

    }

    public static void setIntegrationType(IntegrationType integrationType) {
        if (null == integrationType) throw new NullPointerException("integrationType cannot be null");
        Onepay.integrationType = integrationType;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        if (null == apiKey) throw new NullPointerException("apiKey cannot be null");
        Onepay.apiKey = apiKey;
    }

    public static String getSharedSecret() {
        return sharedSecret;
    }

    public static void setSharedSecret(String sharedSecret) {
        if (null == sharedSecret) throw new NullPointerException("sharedSecret cannot be null");
        Onepay.sharedSecret = sharedSecret;
    }

    public static String getCallbackUrl() {
        return callbackUrl;
    }

    public static void setCallbackUrl(String callbackUrl) {
        if (null == callbackUrl) throw new NullPointerException("callbackUrl cannot be null");
        Onepay.callbackUrl = callbackUrl;
    }

    public static String getAppScheme() {
        return appScheme;
    }

    public static void setAppScheme(String appScheme) {
        Onepay.appScheme = appScheme;
    }

    @ToString public enum IntegrationType {
        LIVE(""),
        TEST("https://web2desa.test.transbank.cl"),
        MOCK("http://onepay.getsandbox.com");

        @Getter private String apiBase;

        IntegrationType(String apiBase) {
            this.apiBase = apiBase;
        }
    }

    public enum Channel {
        WEB,
        MOBILE,
        APP
    }
}
