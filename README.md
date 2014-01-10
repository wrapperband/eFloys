eFloys
======

eFloys with a few extras

An Experiment in Java Artificial Life - Evolving Floys

"keywords" CONTENT="Alife, Artificial Life, Java, Floys, Emergent Behavior, Genetic Algorithms, Web Site Development, Software Development" 

How to run the EFloys Applet.

Install Netbeans (7.3)

In Netbeans
Open the eFloys project, click on efloys.java then in Run menu click Run File

It is best to rest the system after pressing start evolution. The efloys are often fast, it is usually prudent to slow them down with move slower.

Initially the screen shows all green food type efloys. When you press Scramble, the various coloured floys are predator efloys. They die off if they don't find a green efloy. The green efloys are random generated, but still have an effect on successful novel flockings that are possible. The major effect being the box size.

Unfortunately, the 3 dimensional efloy enhancement and other bug fixes got lost at version 7. eFloys version 8.4 was recovered from version 6.

This versions has its Settings in EFloys.html, set very experimental, with large numbers of efloys.

The eFloys is a program I messed to learn Java with when I worked at Manchester University. At the time I was also supervising a number of PhD projects on application and implications of artificial intelligence techniques.

I kept wishing I was good enough to restart it, or rewrite it in python, but it has some very novel elements.


eFloys 

Press Start evolution, then press Restart, press Scramble All  


They belong to the flocking Alife creatures variety, sharing with them the social tendency to stick together,  and the lifelike emergent behavior which is based on a few simple, local rules. They differ from most other flocking Alife animals by having the following properties:<br>

Territorialism (they defend their territory against intruders)
Potential individualism (each can possess a different personality)
Ability to evolve  (using a Genetic Algorithm code).

The settings are configurable by editing the /eFloys/source/Efloys.html file

<applet code=Efloys.class width=1280 height=720>
<PARAM NAME=MaxSpeed VALUE="4">
<PARAM NAME=BounceSpeed VALUE="2">
<PARAM NAME=ApproachAcceleration VALUE=".2">
<PARAM NAME=CenterAcceleration VALUE="2">
<PARAM NAME=DistBrotherFactor VALUE="10">
<PARAM NAME=DistStrangerFactor VALUE="10">
<PARAM NAME=DistLocalFactor VALUE="50">
<PARAM NAME=CollisionDistance VALUE="50">
<PARAM NAME=CollisionBrotherFactor VALUE="20">
<PARAM NAME=CollisionStrangerFactor VALUE="8">
<PARAM NAME=CollisionLocalFactor VALUE="30">
<PARAM NAME=color VALUE="green">
<PARAM NAME=NumberOfNeighbors VALUE="3">
<PARAM NAME=MutationFactor VALUE="2">
<PARAM NAME=CrossoverFactor VALUE="2">
<PARAM NAME=energy VALUE="10">
<PARAM NAME=safety VALUE="10">
<PARAM NAME=cooperation VALUE="10">
<PARAM NAME=EnergyFactor VALUE="2">
<PARAM NAME=SafetyFactor VALUE="2">
<PARAM NAME=CooperationFactor VALUE="2">
<PARAM NAME=SurviversFactor VALUE="1">
<PARAM NAME=PopulationSize VALUE="2010">
<PARAM NAME=FreeWillFactor VALUE="2" >
<PARAM NAME=LifeSpan VALUE="6">
If you can read this then your browser does not support Java, and you cannot see the eFloys applet.
</applet>

Instructions

The overall movement of the creatures can be made to appear smooth and calm, adjust it by clicking the 'Slower' or 'Faster' buttons (click several times to reach the desired effect). 

Click the 'Scramble' button to introduce variety in the population. 
Click the 'ScrambleAll' button to Adds 10 % more food (in develop). 
Insert a green stranger and watch the local eFloys chase and attack him. 
Click 'Start Evolution' to start the evolutionary process, where each generation begins when the older generation kills a stranger. <BR>
Click the 'Reset' button to Restart with a random set - change screen / aquarium size.

