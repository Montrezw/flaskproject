package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.python
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Build : BuildType({
    name = "Build"

    params {
        param("saysomething", "hola Teamcity")
    }

    vcs {
        root(HttpsGithubComMontrezwFlaskprojectRefsHeadsMain)
    }
steps {
    python {
        id = "python_runner"
        command = file {
            filename = "sayhello.py"
        }
    }
    script {
        name = "printer"
        id = "printer"
        scriptContent = """
            for i in {1..5}; do
              python3 sayhello.py
            done
        """.trimIndent()
    }
    script {
        name = "print2"
        id = "print2"
        scriptContent = "python3 sayhello.py"
    }
}
    triggers {
        vcs {
        }
    }
})