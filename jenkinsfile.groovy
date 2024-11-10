node('agent1') {
  def mvnHome
  def tomcatUser = 'ubuntu'
  def tomcatHost = '172.31.9.248'
  def tomcatPath = '/home/ubuntu/tomcat/webapps/'
  stage('Checkout') {
// Checkout source code from Git
     git branch: 'main',  url: 'https://github.com/guruvenkatakrishna/java-example/blob/main/jenkinsfile.groovy'
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
       
      sh "sudo cp /home/ubuntu/jenkins/workspace/jenkinsfile.groovy/target/*.war ${tomcatPath}"
   }
}

