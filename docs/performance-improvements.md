# Performance improvements in this fork

This fork adds a small, reviewable set of UI and memory improvements for workloads that produce long terminal output or display large workspaces. They are intended to reduce avoidable work; they are not a substitute for profiling on the target phone.

## Changes

- Terminal output is held in a tail buffer capped at the same 200,000-character limit already used for project-terminal history. This prevents a long-running command from retaining an unbounded transcript in memory.
- The global terminal keeps only the most recent 100 command results, matching the existing project-terminal history bound. Project-terminal history serialization is moved off the UI dispatcher.
- Live terminal state is published at most about 10 times per second rather than once per read chunk. Package confirmation checks still run on each chunk, and project preview URLs are checked on each UI refresh.
- File-viewer line splitting is memoized for the displayed content, avoiding repeated full-file splitting during unrelated Compose recompositions.
- Workspace directory, visible-entry, and child-count calculations are memoized until their input files or expanded directories change.
- Unit tests cover the bounded text buffer's truncation behavior.

## Validation

Run the Android project checks from the repository root:

```shell
./gradlew testDebugUnitTest assembleDebug
```

The build requires the Android SDK, JDK 17, NDK `26.1.10909125`, and CMake `3.22.1` as documented in the main README. A passing local build and device benchmark should be recorded before claiming a measured speedup.
