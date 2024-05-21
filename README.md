# My Jokes
A sample app to fetch and keep in favorites some jokes.

## How to run
You need version `2023.3.1 Patch 1` of Android Studio Jellyfish to run the App.

No specific dependencies, should be runnable on the fly once project has been fetched

## Architecture
- Each feature is inside a dedicated module(under features package)
- Network module to encapsulate generic networking logic (http client, adapter, logging...)
- A datasource module to have single source of truth between remote and locale data
- A design system module to reuse as much as possible ui components

For unit test and integration test no mock libraries are used, all APIs have a data set of fixtures / custom implementations.
Reason why are better explain by the Compose Team from Google [here](https://android.googlesource.com/platform/frameworks/support/+/refs/heads/androidx-core-core-role-release/docs/do_not_mock.md)