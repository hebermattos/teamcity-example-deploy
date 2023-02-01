package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.FTPUpload
import jetbrains.buildServer.configs.kotlin.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.buildSteps.dotnetPublish
import jetbrains.buildServer.configs.kotlin.buildSteps.dotnetRestore
import jetbrains.buildServer.configs.kotlin.buildSteps.dotnetTest
import jetbrains.buildServer.configs.kotlin.buildSteps.ftpUpload
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object ProdDeploy : BuildType({
    name = "dev deploy"

    vcs {
        root(HttpsGithubComHebermattosStarsApiGatewayGitRefsHeadsMaster)
    }

    steps {
        dotnetRestore {
            name = "Restore"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        dotnetBuild {
            name = "Build"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        dotnetTest {
            name = "Test"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        dotnetPublish {
            name = "Publish"
            projects = "stars-api.csproj"
            configuration = "Release"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        ftpUpload {
            name = "deploy"
            targetUrl = "ftp://deploytest.somee.com/www.deploytest.somee.com"
            securityMode = FTPUpload.SecurityMode.NONE
            dataChannelProtection = FTPUpload.DataChannelProtectionMode.DISABLE
            authMethod = usernameAndPassword {
                username = "hebermattos"
                password = "******"
            }
            transferMode = FTPUpload.TransferMode.AUTO
            sourcePath = """C:\TeamCity\buildAgent\work\d5a220e5b2acfe35\bin\Release\net7.0\publish\"""
        }
    }

    triggers {
        vcs {

            enforceCleanCheckout = true
        }
    }
})
