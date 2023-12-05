plugins {
    kotlin("jvm") version "1.9.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}

for(file in fileTree("src")) {
    if(file.name.matches(Regex("Day\\d{2}.kt"))) {
        tasks.register<JavaExec>(file.name.removeSuffix(".kt")) {
            group = "run"
            description = "Runs ${file.name}"
            classpath = sourceSets["main"].runtimeClasspath
            mainClass = file.name.removeSuffix(".kt")
        }
    }
}