buildscript{
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0") // Example Gradle version
        classpath("com.google.gms:google-services:4.4.2") // Google services plugin
    }

}
plugins {
    id("com.android.application") version "8.8.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply true



}