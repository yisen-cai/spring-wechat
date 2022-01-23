plugins {
    kotlin("jvm")
    id("java")
    id("java-library")
    id("maven-publish")
    id("signing")
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

repositories {
    mavenLocal()
    mavenCentral()
}

val springBootVersion = "2.4.1"

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
    }
}


dependencies {
    api(project(":wechat-library"))

    // Generate its dependencies bean(Conditional Metadata) under META-INF(META-INF/spring-autoconfigure-metadata.properties) folder
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    // Creating IDE friendly Configuration Metadata
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
                description.set("Support some Wechat utils and integrate with Spring.")
                url.set("https://github.com/yisen-cai/spring-wechat/tree/main/spring-boot-starter-wechat")
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