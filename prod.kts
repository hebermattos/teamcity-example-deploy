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
            path = """C:\Users\HeberMattos\backup\winscp-download.bat"""
            arguments = "%backupfolder%"
            param("script.content", """C:\Users\HeberMattos\backup\winscp-download.bat""")
        }
        ftpUpload {
            name = "deploy site 1"
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
        ftpUpload {
            name = "deploy site 2"
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
})
