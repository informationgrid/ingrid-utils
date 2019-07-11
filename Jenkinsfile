pipeline {
    agent any

    parameters {
        booleanParam(name: "RELEASE", description: "Build a release from current commit.", defaultValue: false)
        string(name: 'releaseVersion', defaultValue: '0.0.1', description: 'What is the version of the release?')
        string(name: 'nextVersion', defaultValue: '0.0.2', description: 'What is the next development version? "-SNAPSHOT" will be appended automatically!')
        booleanParam(name: "deleteOldReleaseBranch", description: "Should the release branch from a previous aborted release be deleted.", defaultValue: false)
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '5'))
    }

    stages {
        stage('Build') {
            when {
                not { expression { params.RELEASE } }
            }
            steps {
                withMaven(
                    // Maven installation declared in the Jenkins "Global Tool Configuration"
                    maven: 'Maven3',
                    // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
                    // Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
                    mavenSettingsConfig: '2529f595-4ac5-44c6-8b4f-f79b5c3f4bae'
                ) {

                    // Run the maven build
                    sh 'mvn clean deploy -Dmaven.test.failure.ignore=true'

                } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe & FindBugs reports...
            }
        }

        stage("Release") {
            when {
                expression { params.RELEASE }
            }
            steps {
                sh "git checkout origin develop"
                sh "git pull"

                withMaven(
                    maven: 'Maven3',
                    mavenSettingsConfig: '2529f595-4ac5-44c6-8b4f-f79b5c3f4bae'
                ) {
                    // sh "git branch | grep "release/" | xargs git branch -D"
                    // sh "git tag -d 5.1.0.1"
                    sh "mvn jgitflow:release-start -DreleaseVersion=${params.releaseVersion} -DdevelopmentVersion=${params.nextVersion}-SNAPSHOT -DallowUntracked -DperformRelease=true"
                    sh "mvn jgitflow:release-finish -DallowUntracked"
                }
                withCredentials([usernamePassword(credentialsId: '77647a76-a18e-4ce0-8433-a61ab69bbe9f', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                    sh "git push --all https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/informationgrid/ingrid-utils"
                    sh "git push --tags"
                }
            }
        }

        stage ('SonarQube Analysis'){
            steps {
                withMaven(
                    maven: 'Maven3',
                    mavenSettingsConfig: '2529f595-4ac5-44c6-8b4f-f79b5c3f4bae'
                ) {
                    withSonarQubeEnv('Wemove SonarQube') {
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.4.0.905:sonar'
                    }
                }
            }
        }
    }
    post {
        changed {
            // send Email with Jenkins' default configuration
            script { 
                emailext (
                    body: '${DEFAULT_CONTENT}',
                    subject: '${DEFAULT_SUBJECT}',
                    to: '${DEFAULT_RECIPIENTS}')
            }
        }
    }
}
