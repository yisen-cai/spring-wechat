plugins {
    kotlin("jvm") version "1.5.10"
}

val releaseRepoUrl by extra("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
val snapshotRepoUrl by extra("https://oss.sonatype.org/content/repositories/snapshots/")


allprojects {
    group = "com.glancebar.wechat"
    version = "0.0.3"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}