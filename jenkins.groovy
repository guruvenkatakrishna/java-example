node('agent1') {
  def mvnHome
  def tomcatUser = 'ubuntu'
  def tomcatHost = '172.31.11.49'
  def tomcatPath = '/home/ubuntu/tomcat/webapps/'
  stage('Checkout') {
// Checkout source code from Git
     git branch: 'master',  url: 'https://github.com//HelloWorld.git'
  }	
  stage('Build') {
   // Set Maven tool
     mvnHome = tool 'Maven'
    // Run Maven build
     sh "'${mvnHome}/bin/mvn' clean package"
// Archive the build artifacts
     archiveArtifacts artifacts: '**/target/*.war'
   }
   stage('Deploy') {
       
      sh "sudo cp /home/ubuntu/jenkins/workspace/jenkins.groovy/target/*.war ${tomcatPath}"
   }
}

