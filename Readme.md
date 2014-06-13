This sample project demonstrates a simple blog engine made with pure Java EE.

Quick start:
* Download STS from http://spring.io/tools/sts
* Download JBoss 7.1.1 Final from http://jbossas.jboss.org/downloads/
* Install Application Server Provider from JBoss Developer Tools: Help > Eclipse Marketplace > JBoss Kepler 4.1.2  > JBoss AS Tools and JBoss Maven Integration
* Eclipse: Add new JBoss instance to the server view
* Clone https://github.com/schakko/hackcamp-javaee-sample.git
* File > General > Import Projects > $clonedDirectory
* Eclipse: Right click on imported project > Project Facet > Enable Java and Dynamic Web Module. Without Dynamic Web Module Eclipse is unable to deploy the project into JBoss 
* Eclipse: Right click on JBoss server > Add and remove > Select hackcamp-javaee
* Eclipse: Right click on project > Deployment Assembly and check that only the following assignments are defined:
 * src/main/java -> WEB-INF/classes
 * src/main/resources -> WEB-INF/classes
 * src/main/webapp -> /
 * Maven Dependencies -> WEB-INF/lib
* Open http://localhost:8080/hackcamp-javaee and login with username "username" and password "password"
