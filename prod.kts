package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.FTPUpload
import jetbrains.buildServer.configs.kotlin.buildSteps.exec
import jetbrains.buildServer.configs.kotlin.buildSteps.ftpUpload

object ProdDeploy_2 : BuildType({
    name = "prod deploy"
    steps {
        exec {
            name = "backup"
            path = "%backupscript%"
            arguments = "%backupfolder%"
            param("script.content", """C:\Users\HeberMattos\backup\winscp-download.bat""")
        }
        ftpUpload {
            name = "deploy site 1"
            targetUrl = "ftp://deploytest.somee.com/%sitefolder%"
            securityMode = FTPUpload.SecurityMode.NONE
            dataChannelProtection = FTPUpload.DataChannelProtectionMode.DISABLE
            authMethod = usernameAndPassword {
                username = "hebermattos"
                password = "******"
            }
            transferMode = FTPUpload.TransferMode.AUTO
            sourcePath = "%binfolder%"
        }
        ftpUpload {
            name = "deploy site 2"
            targetUrl = "ftp://deploytest.somee.com/%sitefolder%"
            securityMode = FTPUpload.SecurityMode.NONE
            dataChannelProtection = FTPUpload.DataChannelProtectionMode.DISABLE
            authMethod = usernameAndPassword {
                username = "hebermattos"
                password = "******"
            }
            transferMode = FTPUpload.TransferMode.AUTO
            sourcePath = "%binfolder%"
        }
    }})
