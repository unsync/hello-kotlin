import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
	const val kotlin = "1.3.72"
	const val junit = "5.6.2"
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
        classpath("org.jetbrains.kotlin:kotlin-allopen:1.3.72")
    }
}

plugins {
	kotlin("jvm") version "1.3.72"
}

group = "kotlin.hello"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly: Configuration by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")

    implementation("org.apache.commons:commons-lang3:3.9")

	testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
