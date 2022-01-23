plugins {
    kotlin("jvm")
    id("java")
    id("java-library")
    id("maven-publish")
    id("signing")
}


repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-web:5.3.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

    // implementation("com.squareup.okhttp3:okhttp:4.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}


publishing {
    repositories {
        maven {
            name = "ossrh"
            val releaseRepoUrl: String by rootProject.extra
            val snapshotRepoUrl: String by rootProject.extra
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotRepoUrl else releaseRepoUrl)
            credentials {
                username = properties["ossrh.username"] as String?
                password = properties["ossrh.password"] as String?
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("Spring Boot Starter Wechat")
                description.set("Support some wechat utils and integrate with Spring.")
                url.set("https://github.com/yisen-cai/spring-wechat/tree/main/wechat-library")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0'")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("yisen-cai")
                        name.set("YISHEN CAI")
                        email.set("yisen614@gmail.com")
                        organization.set("Glancebar")
                        organizationUrl.set("https://glancebar.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/yisen-cai/spring-wechat.git'")
                    developerConnection.set("scm:git:git@github.com:yisen-cai/spring-wechat.git")
                    url.set("https://github.com/yisen-cai/spring-wechat")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}