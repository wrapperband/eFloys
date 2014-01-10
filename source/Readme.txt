<applet code=Efloys.class width=1280 height=720>


params

<PARAM NAME=MaxSpeed VALUE="1">
<PARAM NAME=BounceSpeed VALUE="-.8">
<PARAM NAME=ApproachAcceleration VALUE="-2">
<PARAM NAME=CenterAcceleration VALUE="1.4">
<PARAM NAME=DistBrotherFactor VALUE="6">
<PARAM NAME=DistStrangerFactor VALUE="70">
<PARAM NAME=DistLocalFactor VALUE="40">
<PARAM NAME=CollisionDistance VALUE="70">
<PARAM NAME=CollisionBrotherFactor VALUE="-10">
<PARAM NAME=CollisionStrangerFactor VALUE="-10">
<PARAM NAME=CollisionLocalFactor VALUE="-50">

food colour

<PARAM NAME=color VALUE="green">

fixed params

<PARAM NAME=NumberOfNeighbors VALUE="2">
<PARAM NAME=MutationFactor VALUE="2">
<PARAM NAME=CrossoverFactor VALUE="2">
<PARAM NAME=energy VALUE="10">
<PARAM NAME=safety VALUE="10">
<PARAM NAME=cooperation VALUE="3">

environmental params

<PARAM NAME=EnergyFactor VALUE="3">
<PARAM NAME=SafetyFactor VALUE="3">
<PARAM NAME=CooperationFactor VALUE="-32">
<PARAM NAME=SurviversFactor VALUE="99">
<PARAM NAME=PopulationSize VALUE="7555">
<PARAM NAME=FreeWillFactor VALUE="2" >
<PARAM NAME=LifeSpan VALUE="100">


MaxSpeed read as floating point number, internal default is 5

   - Brick wall eFloy maximum speed enforcement. Unfortunately can sometime breed higher. Needs better implementation.
   - You will always be higher than a negative speed

BounceSpeed read as floating point. reduction / change in speed on bounce. Multiplies speed
