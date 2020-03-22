package org.asamk.signal.manager;

import org.whispersystems.signalservice.api.push.TrustStore;
import org.whispersystems.signalservice.internal.configuration.SignalCdnUrl;
import org.whispersystems.signalservice.internal.configuration.SignalContactDiscoveryUrl;
import org.whispersystems.signalservice.internal.configuration.SignalKeyBackupServiceUrl;
import org.whispersystems.signalservice.internal.configuration.SignalServiceConfiguration;
import org.whispersystems.signalservice.internal.configuration.SignalServiceUrl;
import org.whispersystems.signalservice.internal.configuration.SignalStorageUrl;

import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;

public class BaseConfig {

    public final static String PROJECT_NAME = Manager.class.getPackage().getImplementationTitle();
    public final static String PROJECT_VERSION = Manager.class.getPackage().getImplementationVersion();

    final static String USER_AGENT = PROJECT_NAME == null ? "signal-cli" : PROJECT_NAME + " " + PROJECT_VERSION;
    final static String UNIDENTIFIED_SENDER_TRUST_ROOT = "BXu6QIKVz5MA8gstzfOgRQGqyLqOwNKHL6INkv3IHWMF";
    final static int PREKEY_MINIMUM_COUNT = 20;
    final static int PREKEY_BATCH_SIZE = 100;
    final static int MAX_ATTACHMENT_SIZE = 150 * 1024 * 1024;

    private final static String URL = "https://textsecure-service.whispersystems.org";
    private final static String CDN_URL = "https://cdn.signal.org";
    private final static String SIGNAL_KEY_BACKUP_URL = "https://api.backup.signal.org";
    private final static String STORAGE_URL = "https://storage.signal.org";
    private final static TrustStore TRUST_STORE = new WhisperTrustStore();

    private final static Interceptor userAgentInterceptor = chain ->
            chain.proceed(chain.request().newBuilder()
                    .header("User-Agent", USER_AGENT)
                    .build());

    private final static List<Interceptor> interceptors = Collections.singletonList(userAgentInterceptor);

    private final static byte[] zkGroupServerPublicParams = new byte[]{};

    final static SignalServiceConfiguration serviceConfiguration = new SignalServiceConfiguration(
            new SignalServiceUrl[]{new SignalServiceUrl(URL, TRUST_STORE)},
            new SignalCdnUrl[]{new SignalCdnUrl(CDN_URL, TRUST_STORE)},
            new SignalContactDiscoveryUrl[0],
            new SignalKeyBackupServiceUrl[]{new SignalKeyBackupServiceUrl(SIGNAL_KEY_BACKUP_URL, TRUST_STORE)},
            new SignalStorageUrl[]{new SignalStorageUrl(STORAGE_URL, TRUST_STORE)},
            interceptors,
            zkGroupServerPublicParams
    );

    static final String KEY_BACKUP_ENCLAVE_NAME = "fe7c1bfae98f9b073d220366ea31163ee82f6d04bead774f71ca8e5c40847bfe";
    static final String KEY_BACKUP_MRENCLAVE = "a3baab19ef6ce6f34ab9ebb25ba722725ae44a8872dc0ff08ad6d83a9489de87";

    private BaseConfig() {
    }
}
