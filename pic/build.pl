node {
    /*
   def mvnHome
   def javaHome
   tools {
        maven 'M3'
        jdk 'Jdk8'
    }*/
    stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      //git 'https://github.com/scherzoteller/springsandboxlight.git'
      checkout scm
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      env.JAVA_HOME="${tool 'Jdk8'}"
      env.MAVEN_HOME="${tool 'M3'}"
      env.PATH="${env.JAVA_HOME}/bin:${env.MAVEN_HOME}/bin:${env.PATH}"
   }
   stage('Build') {
      // Run the maven build
      
      sh "mvn -version"
      sh "java -version"
      
      if (isUnix()) {
          //export JAVA_HOME=${javaHome} && export 
          //${mvnHome}/bin/
         sh "'mvn' -Dmaven.test.failure.ignore clean package"
      } else {
          //${mvnHome}\bin\
         bat(/"mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }

}
