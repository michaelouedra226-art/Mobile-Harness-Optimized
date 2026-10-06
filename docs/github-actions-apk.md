# Build and download the test APK

The `Build Android APK` workflow runs on every push to `main` and can also be started manually from **Actions → Build Android APK → Run workflow**.

The job checks out the native submodules, installs JDK 17 and the Android SDK/NDK/CMake versions required by this project, runs the online debug unit tests, and builds the **online ARM64 debug APK**. After a successful run:

1. Open the workflow run in the repository’s Actions tab.
2. Download the `mobile-harness-optimized-online-debug` artifact from the run summary.
3. Extract the ZIP and install the APK on an ARM64 Android 9+ phone.

The APK uses package ID `com.jarves.mh.optimized` and is labeled **Mobile Harness Optimized**. It installs alongside the official app, so it does not require uninstalling that app or deleting its data. It is a development/debug build, not an official signed release. It downloads runtime components as needed; this workflow does not build the large offline APK because the runtime bundle archives are intentionally not checked into this public source repository.

GitHub Actions artifacts expire after 30 days. Android debug signing is for testing and is not a stable production update-signing scheme. Keep backups of projects created in the optimized app. For reliable upgrades that preserve that app’s data, a stable keystore must be configured as protected GitHub Actions secrets; never commit a keystore or its passwords to this public repository.
