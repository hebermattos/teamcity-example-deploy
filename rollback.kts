package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.FTPUpload
import jetbrains.buildServer.configs.kotlin.buildSteps.ftpUpload

object Rollback : BuildType({
    name = "rollback"

    steps {
        ftpUpload {
            name = "deploy backup"
            targetUrl = "ftp://deploytest.somee.com/www.deploytest.somee.com"
            securityMode = FTPUpload.SecurityMode.NONE
            dataChannelProtection = FTPUpload.DataChannelProtectionMode.DISABLE
            authMethod = usernameAndPassword {
                username = "hebermattos"
                password = "******"
            }
            transferMode = FTPUpload.TransferMode.AUTO
            sourcePath = """%backupfolder%\www.deploytest.somee.com"""
        }
    }
})
