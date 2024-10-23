# Repro for KGP 2.1.0-Beta2 regression for android artifact publishing

## Steps

1. `cd publisher/`
2. `./gradlew publish` 
3. `cd ../consumer/`
4. `./gradlew build`

## Expected

Success

## Actual

Failure

```
* What went wrong:
Execution failed for task ':kmpLibrary:extractDebugAnnotations'.
> Error while evaluating property 'hasAndroidAnnotations' of task ':kmpLibrary:extractDebugAnnotations'.
   > Could not resolve all artifacts for configuration ':kmpLibrary:debugCompileClasspath'.
      > Could not resolve org.example.kmplibrary:kmpLibrary:1.0.
        Required by:
            project :kmpLibrary
         > No matching variant of org.example.kmplibrary:kmpLibrary:1.0 was found. The consumer was configured to find a library for use during compile-time, preferably optimized for Android, as well as attribute 'com.android.build.api.attributes.AgpVersionAttr' with value '8.2.2', attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'debug' but:
             - Variant 'metadataApiElements' declares a library, preferably optimized for non-jvm:
                 - Incompatible because this component declares a component for use during 'kotlin-metadata' and the consumer needed a component for use during compile-time
                 - Other compatible attributes:
                     - Doesn't say anything about com.android.build.api.attributes.AgpVersionAttr (required '8.2.2')
                     - Doesn't say anything about com.android.build.api.attributes.BuildTypeAttr (required 'debug')
             - Variant 'metadataSourcesElements' declares a component, preferably optimized for non-jvm:
                 - Incompatible because this component declares documentation for use during 'kotlin-runtime' and the consumer needed a library for use during compile-time
                 - Other compatible attributes:
                     - Doesn't say anything about com.android.build.api.attributes.AgpVersionAttr (required '8.2.2')
                     - Doesn't say anything about com.android.build.api.attributes.BuildTypeAttr (required 'debug')
             - Variant 'releaseApiElements-published' declares a library for use during compile-time, preferably optimized for Android:
                 - Incompatible because this component declares a component, as well as attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'release' and the consumer needed a component, as well as attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'debug'
                 - Other compatible attribute:
                     - Doesn't say anything about com.android.build.api.attributes.AgpVersionAttr (required '8.2.2')
             - Variant 'releaseRuntimeElements-published' declares a library for use during runtime, preferably optimized for Android:
                 - Incompatible because this component declares a component, as well as attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'release' and the consumer needed a component, as well as attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'debug'
                 - Other compatible attribute:
                     - Doesn't say anything about com.android.build.api.attributes.AgpVersionAttr (required '8.2.2')
             - Variant 'releaseSourcesElements-published' declares a component for use during runtime, preferably optimized for Android:
                 - Incompatible because this component declares documentation, as well as attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'release' and the consumer needed a library, as well as attribute 'com.android.build.api.attributes.BuildTypeAttr' with value 'debug'
                 - Other compatible attribute:
                     - Doesn't say anything about com.android.build.api.attributes.AgpVersionAttr (required '8.2.2')


```

## Details

Regression started in KGP 2.1.0-Beta2 due to https://github.com/JetBrains/kotlin/commit/d02ef9e59183f5d04f9985795b08b0eaf0b753a6 that causes a new attribute to be added to the Gradle module files

```
"com.android.build.api.attributes.BuildTypeAttr": "release",
```

this makes it so that consumers that are consuming this library and are building debug, are no longer able to use it.
