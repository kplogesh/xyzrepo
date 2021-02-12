pipeline {
    agent any 

    stages {
        stage('Build'){
            steps {
                bat "${env.SAG_HOME}/common/lib/ant/bin/ant -DSAGHome=${env.SAG_HOME} -DSAG_CI_HOME=${env.SAG_CI_SIT_HOME} -DprojectName=${env.JOB_NAME} build"
            }
        }
        stage('Deploy') {
            steps {
		bat "${env.SAG_HOME}/common/lib/ant/bin/ant -DSAGHome=${env.SAG_HOME} -DSAG_CI_HOME=${env.SAG_CI_SIT_HOME} -DprojectName=${env.JOB_NAME} deploy"
            }
        }
        stage('Review') {
            steps {
		bat "${env.ISCCR_HOME}/CodeReview.cmd -Dcode.review.directory=${env.SAG_HOME}/IntegrationServer/instances/default/packages -Dcode.review.pkgname=CEFAkamaiUserInfo -Dcode.review.pkgprefix=CEF -Dcode.review.folder-prefix=CEF -Dcode.review.output.directory=C:/Logesh/Jenkins/.jenkins/workspace/FirstPipeline/CodeReviewReport"
            
			publishHTML (target : [allowMissing: false,
			 alwaysLinkToLastBuild: false,
			 keepAll: true,
			 reportDir: 'C:/Logesh/Jenkins/.jenkins/workspace/FirstPipeline/',
			 reportFiles: 'CEFAkamaiUserInfo__CodeReviewReport__html-package.html',
			 reportName: 'Code Review Reports',
			 reportTitles: 'Code Review Report'])
			}
        }
		stage('Test') {
            steps {
		bat "${env.SAG_HOME}/common/lib/ant/bin/ant -DSAGHome=${env.SAG_HOME} -DSAG_CI_HOME=${env.SAG_CI_SIT_HOME} -DprojectName=${env.JOB_NAME} test"
		junit 'report/'
            }
        }
    }
}
