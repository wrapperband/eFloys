//      Dr A Doyle
//	Ariel Dolan
//   Notes
//   Required enhancements. 3D movement, creature for food, trainable food creature, background colour
//   Species colour, object detection, object background image, better breading, ie new species
//   Learnable movement groups. neural nets, object identification - action learning, instinct passed to offspring
//   Change bottons round to clear screen. evolve to same position, open side tank, energy remaining (sic),

// food source() - how food grows and dies, it
// movement memory, turn(amount) forward(amount)
// state -Hungry(Time since meal) Tired(0) Scared(0)

// variable efloy size setable in params - form for input data?
// seperate food and animal base params. 
// manual macros for food movement.
// change evolving to be rnd select each trait from parents (not average as now|)
// message to screen top - number of eFloys alive
// enhance ScrambleAll - convert food - add 5% more animals / 

// add soft restart - all to food / type 0
// cycle add 10 % food

// all are food (for a stranger)
// Check generations
// document parameters
// save and retreive floys from disk
// loose energy when move
// show messages on top bar
// add extra parameters, + public variable of scatterAll proportion

// BUG SCRAMBLE START EVOLUTION? EVEN IF OFF

// scramble all more random food, insert stranger - don't let food eat !..
// change / check when breeding happens?
// random return to centre region when at edge

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.net.*;
import java.util.Date;
// import java.util.logging.Level;
// import java.util.logging.Logger;




class EfloyCanvas extends Canvas
{
	Image image;
	Color color;
	boolean first;
	boolean reset;
	Image OffImg;
	Graphics OffGra;


	public EfloyCanvas(Image img)	{

		super();
 		image = img;
		color = Color.white;
		setBackground(Color.lightGray);
		setForeground(Color.white);
		first = true;
		reset = false;
		//OffImg = createImage(size().width, size().height);

	}

	public EfloyCanvas(Color c)	{

		super();
 		image = null;
		setBackground(Color.lightGray);
		setForeground(Color.white);
		color = c;
		first = true;
		reset = false;
		//OffImg = createImage(size().width, size().height);

	}

	public void Clear() {

		reset = true;
		repaint();

	}


	public void paint(Graphics g) {

		int i;  // oldx, oldy;
		Efloy Efloy;

                		/*
		if (reset) {
			g.setColor(Color.black);
			g.fillRect(0,0,size().width,size().height);
			reset = false;
			return;
		}

		if (image != null)
			g.drawImage(image,0,0,this);
		else {
		}
		*/

		if (Efloys.Efloys != null) {
			for (i=0;i<Efloys.Efloys.length;i++)
	 		{
				try {
					Efloy = Efloys.Efloys[i];
					Efloy.GetNeighbors();
                                          //  oldx = (int) Efloy.x;
                                          //  oldy = (int) Efloy.y;
	 				Efloy.Process();
					Efloy.Draw(g);
                           
				}
				catch (Exception e) {
					//System.out.println("EfloysCanvas Paint. e= "+e.toString());
				}
	 		}
		}

	}


	public void xpaint(Graphics g) {

		int i; // ,  oldx, oldy;
		Efloy Efloy;


		OffImg = createImage(size().width, size().height);

		try {
		    OffGra = OffImg.getGraphics();
		}
	    catch (Exception e) {
			Efloys.appcontext.showStatus("After OffImg e= "+e.toString());
		}


		OffGra.setColor(getBackground());
		OffGra.fillRect(0,0, size().width, size().height);

		for (i=0;i<Efloys.Efloys.length;i++)
	 	{
			Efloy = Efloys.Efloys[i];
			Efloy.GetNeighbors();
                        // oldx = (int) Efloy.x;
                        // oldy = (int) Efloy.y;
                        Efloy.Process();
			Efloy.Draw(OffGra);
      
	 	}

		g.drawImage(OffImg, 0, 0, this);
	}


	Graphics GetGra() {

		return this.getGraphics();
	}
}

public class Efloys extends Applet implements Runnable {

	Thread runner;
	EfloyCanvas canvas;
	EfloyCommand fcommand;
	EfloyInfo finfo;
	EfloyPredefined fpredefined;
	static Efloy[] Efloys;
	static Efloy[] NewFloys;
	Button Start;
	Button Pause;
	Button Rules;
	Button Info;
	Button Control;
	Button Kick;
	Button Slower;
	Button Faster;
	Button Scramble;
        Button ScrambleAll;
	Button Stranger;
	Button Breed;
	static Button Evolution;
	Button Quit;

	Button Help;
	Button Log;
	Button Predefined;
	Button Numbers;
	Button Sound;
	Button Ranges;

	Graphics gra;
	Panel ControlPanel;
	Panel center;
	Panel center1;
	Label MainTitle;
	URL MainPage;
	static EfloyParam params[];
	static EfloyParam fixpars[];
	static EfloyParam envpars[];
	Font ButtonFont;
	Date StartDate;

	int wa,ha,wc,hc;
	int CurrentBehavior;
	int CurrentNum;
	long CurrentStep;
	long CurrentTotalStep;
	static long CurrentGeneration;
	long CurrentRandom;
	long SumFitness;
	boolean running;
	static boolean First;
	static boolean ResetPopulation = true;
	static boolean NewGeneration = false;
	static boolean InEvolution = false;
	static boolean DrawNumbers = false;
	static boolean WithSound = true;
	static boolean InLog = true;
	static boolean LimitedRanges = false;

	static EfloyGeneration FloyGen;
	static Vector HistoryData;

	static int NF;
	static int REVDIST;
	static float ACC;
	static float ACCTOMID;	
	static float MAXSPEED;
	static float BOUNCESPEED;
	static float V0;
	static float KICK;
	static long SLEEP;
	static int MARGIN;
	static int NUMNB;
	static int TYPE;
	static String COLOR;


	static AudioClip joy;
	static AudioClip beep;
        static Image picture;
        static AppletContext appcontext;
	static EfloyLog deb;
	static EfloyLog helpwin;
	static EfloyLog rulewin;
	static Dimension ScreenSize;


	public void init() {
		// int i;
		// Float temp;
		// String s;

	 ButtonFont = new Font("TimesRoman",Font.PLAIN,12);

	    deb = new EfloyLog("Log Window");
	    //deb.toBack();
	    deb.setVisible(false);
            deb.showMsg("");
	    deb.reshape(50,40,540,380);
	    deb.start();

	    helpwin = new EfloyLog("Help Window");
	    //helpwin.toBack();
	    helpwin.setVisible(false);
            helpwin.showMsg("");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		ScreenSize = d;
		int w = d.width;
		int h = d.height;
		helpwin.reshape(0,0,w-2,h-2);
	    //helpwin.reshape(10,40,620,380);
		CreateHelp(helpwin);

	    rulewin = new EfloyLog("Behavioral Rules of eFloys");
	    //rulewin.toBack();
	     rulewin.setVisible(false);
             rulewin.showMsg("");
		rulewin.reshape(2,2,w-2,h-2);
		CreateRules(rulewin);

		setLayout(new BorderLayout());

		Panel bot = new Panel();
		Start = new Button(" Restart ");
                ScrambleAll = new Button(" Scramble All ");
		Scramble = new Button(" Scramble ");
		Stranger = new Button(" Add Green Food ");
		Breed = new Button(" Breed ");
		Evolution = new Button(" Start Evolution ");
		Quit = new Button(" Quit ");

		bot.setFont(ButtonFont);

		bot.add(Start);
                bot.add(ScrambleAll);
                bot.add(Scramble);
		bot.add(Breed);
		bot.add(Evolution);
		bot.add(Stranger);
		add("South",bot);

		Numbers = new Button("Show Numbers");
		Sound = new Button("Turn Sound Off");
		Slower = new Button("Move Slower");
		Faster = new Button("Move Faster");
		Pause = new Button("Pause");
		Ranges = new Button("Limited Ranges");
		


		Help = new Button("Show Help");
		Log = new Button("Show Log");
		Predefined = new Button("Predefined");
		Control = new Button("Edit Properties");
		Info = new Button("Show Info");
		Rules = new Button("Show Rules");
		

		Panel west = new Panel();
		west.setLayout(new GridLayout(6,1));
		west.setFont(ButtonFont);

		west.add(Numbers);
		west.add(Sound);
		west.add(Slower);
		west.add(Faster);
		west.add(Pause);
		west.add(Ranges);

		add("West",west);


		Panel east = new Panel();
		east.setLayout(new GridLayout(6,1));
		east.setFont(ButtonFont);

		east.add(Help);
		east.add(Rules);
		east.add(Log);
		east.add(Predefined);
		east.add(Info);
		east.add(Control);

		add("East",east);


		Panel north = new Panel();
		Font BoldFont = new Font("TimesRoman",Font.BOLD,12);
		north.setFont(BoldFont);
		//north.setBackground(Color.blue);
		//north.setForeground(Color.white);
		//MainTitle = new Label(".                 The eFloys Aquarium                 .");
		MainTitle = new Label(" eFloys Aquarium v8.3 ");
		MainTitle.setForeground(Color.black);
		north.add(MainTitle);
		add("North",north);

		appcontext = getAppletContext();
		// picture = getImage(getCodeBase(),"Efloys1.gif");
		joy = getAudioClip(getCodeBase(), "joy.au");
		beep = getAudioClip(getCodeBase(), "Beep.au");


		canvas = new EfloyCanvas(Color.red);
		//canvas = new EfloyCanvas(picture);
		add("Center",canvas);
		// canvas.repaint();
		gra = canvas.GetGra();

		center = new Panel();
		center.setLayout(new GridLayout(2,2));
		center.add(new Button("B1"));
		center.add(new Button("B2"));
		center.add(new Button("B3"));
		center.add(new Button("B4"));
		//add("Center",center);
		//center.repaint();
		//remove(center);

		center1 = new Panel();
		center1.setLayout(new GridLayout(2,2));
		center1.add(new Button("B1a"));
		center1.add(new Button("B2a"));
		center1.add(new Button("B3a"));
		center1.add(new Button("B4a"));
		//add("Center",center1);

		HistoryData = new Vector();

		//Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		//resize(d.width-20,250);
		// resize(1680,1200);  ajd - test

		CurrentBehavior = 0;
		First = true;

	}

	public void destroy() {

		deb.dispose();
		helpwin.dispose();
		rulewin.dispose();

	}


	public void start()
	{
		if (runner == null)
		{
			runner= new Thread(this);
			runner.start();
			running = true;
			CurrentStep = 0;
			CurrentTotalStep = 0;
			CurrentRandom = 0;
			CurrentGeneration = 0;
		}
	}
	
	public void stop()
	{
		if (runner!=null)
		{
            
                // runner.wait();
                runner.interrupt();
                runner.stop();
                runner = null;
		running = false;
            			
		}
	}


	public void run() {

		if (First) {

                    InitParamsExtended();
			GetParameters();
			Restart(false);
			randemize(); // ajd test
			First = false;
                        
		}
	

		while (true) {

			// if (First) Restart(false);
			// First = false;

			CurrentStep++;
			CurrentTotalStep++;
		 	// if (Math.random()< (double) KICK) randemize();
			if (((InEvolution) && (NewGeneration)) || ((InEvolution) && (CurrentStep > 200000))) {
				StartDate = new Date();
				deb.showMsg("New Generation #"+(CurrentGeneration+1)+" started at "+StartDate.toLocaleString());
				CreateNewGeneration();
                                 
			}
		 	canvas.repaint();
		 	try { Thread.sleep(SLEEP);}
		 	catch (InterruptedException e) {
				showStatus("Thread.sleep InterruptedException "+e.toString());
			}
		}
		


	}


	float GetOneParameter(String name, float def) {

		String s;
		Float temp;
		float FloatPar;

		s=getParameter(name);
		if (s==null)
			FloatPar=def;
		else {
			temp = new Float(s);
			FloatPar = temp.floatValue();
		}

		return FloatPar;

	}


	void GetParameters() {

		// String s;
		// Float temp;
		// int IntPar;
		// float FloatPar;

		params[0].value = GetOneParameter("MaxSpeed",5f);
		params[1].value = GetOneParameter("BounceSpeed",0.8f);
		params[2].value = GetOneParameter("ApproachAcceleration",0.3f);
		params[4].value = GetOneParameter("CenterAcceleration",0.1f);
		params[5].value = GetOneParameter("DistBrotherFactor",1f);
		params[6].value = GetOneParameter("DistStrangerFactor",20f);
		params[7].value = GetOneParameter("DistLocalFactor",0f);
		params[8].value = GetOneParameter("CollisionDistance",200f);
		params[9].value = GetOneParameter("CollisionBrotherFactor",-1f);
		params[10].value = GetOneParameter("CollisionStrangerFactor",30f);
		params[11].value = GetOneParameter("CollisionLocalFactor",-40f);

		fixpars[5].value = GetOneParameter("NumberOfNeighbors",2f);
		fixpars[6].value = GetOneParameter("MutationFactor",0.1f);
		fixpars[7].value = GetOneParameter("CrossoverFactor",1f);
		fixpars[8].value = GetOneParameter("energy",10f);
		fixpars[9].value = GetOneParameter("safety",10f);
		fixpars[10].value = GetOneParameter("cooperation",10f);

		envpars[5].value = GetOneParameter("EnergyFactor",1f);
		envpars[6].value = GetOneParameter("SafetyFactor",1f);
		envpars[7].value = GetOneParameter("CooperationFactor",0f);
		envpars[8].value = GetOneParameter("SurviversFactor",0f);
		envpars[12].value = GetOneParameter("PopulationSize",15f);
		envpars[13].value = GetOneParameter("FreeWillFactor",0.05f);
		envpars[14].value = GetOneParameter("LifeSpan",50f);

		NF = (int) envpars[12].value;
		SLEEP = (long) envpars[3].value;
		KICK =  envpars[13].value;

}


	public void randemize()
	{
		int j,k,n;

		CurrentRandom++;
		for (k=0;k<Efloys.length;k++) {
			Efloy ng = Efloys[k];
			for (j=0;j<ng.NumberOfNeighbors;j++) {
				n = (int) (Math.random()*(Efloys.length-1));
				//if (n == k) {
				//	if (n == 0) n++;
				//	else n--;
				//}
				ng.neighbors[j] = Efloys[n];
			}
		}
	}


	public void scramble( boolean Food) {

		int i;
                int numberFood2change;
                double PropNotFood; // proportion not food ajd
                // Random generator = new Random();
                PropNotFood = 0.9;                
                
                
                if (Food == true) {
                    numberFood2change = (int)(Efloys.length * PropNotFood);
                } else { 
                    numberFood2change = Efloys.length;
                }
		
                // ajd make food last in list for scrambleAll
                SortFloys(Efloys);  // ajd test added sort before food / stranger release.
                
		for (i = (Efloys.length - numberFood2change); i<Efloys.length ;i++) {
			Efloys[i].shuffle(false,(float) 0.4, Food); // changed mutate rate  from .3
		}

		// GetSumFitness();  ajd

//		for (i=0;i<Efloys.length;i++) {
//			if (Efloys[i].type == 0) {
//                            Efloys[i].energy = Efloys[i].LifeSpan;
//                        }
//
//                        else {
//                                Efloys[i].vx = (float)( Efloys[i].MaxSpeed * generator.nextGaussian() );
//                                Efloys[i].vy = (float)( Efloys[i].MaxSpeed * generator.nextGaussian() );
//                        }
//
//		}

	}



	public void CreateNewGeneration() {

		//w = GetWinner();
		RecordGeneration();

		CurrentGeneration++;
		PreScale();
		Breed();
	        // ajd ReleaseStranger();
		NewGeneration = false;
		CurrentStep = 0;
			
		showStatus("Generation #" + CurrentGeneration);
		
	}


	public int GetWinner() {

		int i, winner, e;
			
		e = 0;
		winner = 0;

		for (i=0;i<Efloys.length;i++) {
			Efloys[i].GetFitness();
			if (Efloys[i].fitness > e) {
				e = Efloys[i].fitness;
				winner = i;
			}
		}

		return winner;
	}


	public void RecordGeneration() {

		int i, w, fit;
		float SpeedSum, SpeedAvg, AccSum, AccAvg;
		float DllSum, DllAvg, DslSum, DslAvg, DlsSum, DlsAvg;
		float CllSum, CllAvg, CslSum, CslAvg, ClsSum, ClsAvg;
			
		fit = 0;
		w = 0;
		SpeedSum = 0;
                SpeedAvg = 0;
		AccSum = 0;
		DllSum = 0;
		DlsSum = 0;
		DslSum = 0;
		CllSum = 0;
		ClsSum = 0;
		CslSum = 0;

		for (i=0;i<Efloys.length;i++) {
		SpeedSum = ( SpeedSum + (float) Math.sqrt((Efloys[i].vx * Efloys[i].vx) + ( Efloys[i].vy * Efloys[i].vy)) );
			AccSum = AccSum + Efloys[i].ApproachAcceleration;
			DllSum = DllSum + Efloys[i].DistBrotherFactor;
			DlsSum = DllSum + Efloys[i].DistStrangerFactor;
			DslSum = DllSum + Efloys[i].DistLocalFactor;
			CllSum = CllSum + Efloys[i].CollisionBrotherFactor;
			ClsSum = CllSum + Efloys[i].CollisionStrangerFactor;
			CslSum = CllSum + Efloys[i].CollisionLocalFactor;

			Efloys[i].GetFitness();
			if (Efloys[i].fitness > fit) {
				fit = Efloys[i].fitness;
				w = i;
			}
		}

		SpeedAvg = (float) SpeedSum/Efloys.length;
		AccAvg = (float) AccSum/Efloys.length;
		DllAvg = (float) DllSum/Efloys.length;
		DlsAvg = (float) DlsSum/Efloys.length;
		DslAvg = (float) DslSum/Efloys.length;
		CllAvg = (float) CllSum/Efloys.length;
		ClsAvg = (float) ClsSum/Efloys.length;
		CslAvg = (float) CslSum/Efloys.length;

		Efloys[w].GetFitness();
		FloyGen = new EfloyGeneration(CurrentGeneration, CurrentStep, 0, Efloys[w].id, Efloys[w].chrom, 
			Efloys[w].fitness, Efloys[w].energy, Efloys[w].safety, 
			Efloys[w].cooperation, Efloys[w].MaxSpeed,Efloys[w].ApproachAcceleration,
			SpeedAvg,AccAvg,DllAvg,DlsAvg,DslAvg,CllAvg,ClsAvg,CslAvg);

		HistoryData.addElement(FloyGen);

	}


	void InitParamsLimited() {

		params = new EfloyParam[12];

		params[0]  = new EfloyParam(1f,11f,0.5f,5f,"MaxSpeed",true);
		params[1]  = new EfloyParam(0.2f,2.2f,0.1f,0.8f,"BounceSpeed",true);
		params[2]  = new EfloyParam(0.1f,1.1f,0.05f,0.3f,"ApproachAcceleration",true);
		params[3]  = new EfloyParam(0.1f,1.1f,0.05f,0.3f,"RetreatAcceleration",true);
		params[4]  = new EfloyParam(0f,0.2f,0.01f,0.1f,"CenterAcceleration",true);

		params[5]  = new EfloyParam(0f,10f,0.5f,1f,"DistBrotherFactor",true);
		params[6]  = new EfloyParam(0f,50f,2.5f,20f,"DistStrangerFactor",true);
		params[7]  = new EfloyParam(-50f,50f,0.5f,0f,"DistLocalFactor",true);

		params[8]  = new EfloyParam(0f,500f,25f,200f,"CollisionDistance",true);

		params[9]  = new EfloyParam(-10f,0f,0.5f,-1f,"CollisionBrotherFactor",true);
		params[10] = new EfloyParam(0f,100f,5f,30f,"CollisionStrangerFactor",true);
		params[11] = new EfloyParam(-100f,0f,5f,-40f,"CollisionLocalFactor",true);


		fixpars = new EfloyParam[12];

		fixpars[0] = new EfloyParam(1f,100f,1f,(float) CurrentNum,"id",false);
		fixpars[1] = new EfloyParam(1f,100f,1f,0f,"father",false);
		fixpars[2] = new EfloyParam(1f,100f,1f,0f,"mother",false);
		fixpars[3] = new EfloyParam(0f,10f,1f,0f,"type",false);
		fixpars[4] = new EfloyParam(0f,13f,1f,5f,"color",false);
		fixpars[5] = new EfloyParam(1f,10f,1f,2f,"NumberOfNeighbors",false);
		fixpars[6] = new EfloyParam(0f,0.2f,0.01f,0.1f,"MutationFactor",false);
		fixpars[7] = new EfloyParam(0f,1f,0.1f,1f,"CrossoverFactor",false);

		fixpars[8] = new EfloyParam(0f,100f,10f,10f,"energy",true);
		fixpars[9] = new EfloyParam(0f,100f,10f,10f,"safety",true);
		fixpars[10] = new EfloyParam(0f,100f,10f,10f,"cooperation",true);
		
		fixpars[11] = new EfloyParam(0f,100f,10f,10f,"fitness",true);



		envpars = new EfloyParam[15];

		float h = (float) canvas.size().height;
		float w = (float) canvas.size().width;

		envpars[0] = new EfloyParam(100f,1000f,10f,w,"width",false);
		envpars[1] = new EfloyParam(100f,1000f,10f,h,"height",false);
		envpars[2] = new EfloyParam(1f,10f,1f,4f,"v0",false);
		envpars[3] = new EfloyParam(5f,50f,5f,10f,"sleep",false);
		envpars[4] = new EfloyParam(0f,50f,5f,30f,"margin",false);

		envpars[5] = new EfloyParam(0f,10f,1f,1f,"EnergyFactor",true);
		envpars[6] = new EfloyParam(0f,10f,1f,1f,"SafetyFactor",true);
		envpars[7] = new EfloyParam(0f,10f,1f,0f,"CooperationFactor",true);
		envpars[8] = new EfloyParam(0f,100f,10f,0f,"SurviversFactor",true);

 		envpars[9] = new EfloyParam(-10f,10f,1f,1f,"MaxEnergyDose",true);
		envpars[10] = new EfloyParam(-10f,10f,1f,1f,"MaxSafetyDose",true);
		envpars[11] = new EfloyParam(-10f,10f,1f,1f,"MaxCooperationDose",true);

		envpars[12] = new EfloyParam(5f,50f,5f,15f,"PopulationSize",true);
		envpars[13] = new EfloyParam(0.01f,0.1f,0.01f,0.05f,"FreeWillFactor",true);
		envpars[14] = new EfloyParam(10f,200f,10f,50f,"LifeSpan",true);

	}


	void InitParamsExtended() {

		params = new EfloyParam[12];

		params[0]  = new EfloyParam(1f,11f,0.5f,5f,"MaxSpeed",true);
		params[1]  = new EfloyParam(0.2f,2.2f,0.1f,0.8f,"BounceSpeed",true);
		params[2]  = new EfloyParam(0.1f,2.1f,0.1f,0.3f,"ApproachAcceleration",true);
		params[3]  = new EfloyParam(0.1f,2.1f,0.1f,0.3f,"RetreatAcceleration",true);
		params[4]  = new EfloyParam(0f,0.2f,0.01f,0.1f,"CenterAcceleration",true);

		params[5]  = new EfloyParam(-10f,10f,1f,1f,"DistBrotherFactor",true);
		params[6]  = new EfloyParam(-50f,50f,5f,20f,"DistStrangerFactor",true);
		params[7]  = new EfloyParam(-50f,50f,5f,0f,"DistLocalFactor",true);

		params[8]  = new EfloyParam(0f,500f,25f,200f,"CollisionDistance",true);

		params[9]  = new EfloyParam(-10f,10f,1f,-1f,"CollisionBrotherFactor",true);
		params[10] = new EfloyParam(-100f,100f,10f,30f,"CollisionStrangerFactor",true);
		params[11] = new EfloyParam(-100f,100f,10f,-40f,"CollisionLocalFactor",true);



		fixpars = new EfloyParam[12];

		fixpars[0] = new EfloyParam(1f,100f,1f,(float) CurrentNum,"id",false);
		fixpars[1] = new EfloyParam(1f,100f,1f,0f,"father",false);
		fixpars[2] = new EfloyParam(1f,100f,1f,0f,"mother",false);
		fixpars[3] = new EfloyParam(0f,10f,1f,0f,"type",false);
		fixpars[4] = new EfloyParam(0f,13f,1f,5f,"color",false);
		fixpars[5] = new EfloyParam(1f,10f,1f,2f,"NumberOfNeighbors",false);
		fixpars[6] = new EfloyParam(0f,0.2f,0.01f,0.1f,"MutationFactor",false);
		fixpars[7] = new EfloyParam(0f,1f,0.1f,1f,"CrossoverFactor",false);

		fixpars[8] = new EfloyParam(0f,100f,10f,10f,"energy",true);
		fixpars[9] = new EfloyParam(0f,100f,10f,10f,"safety",true);
		fixpars[10] = new EfloyParam(0f,100f,10f,10f,"cooperation",true);
		
		fixpars[11] = new EfloyParam(0f,100f,10f,10f,"fitness",true);



		envpars = new EfloyParam[15];

		float h = (float) canvas.size().height;
		float w = (float) canvas.size().width;

		envpars[0] = new EfloyParam(100f,1000f,10f,w,"width",false);
		envpars[1] = new EfloyParam(100f,1000f,10f,h,"height",false);
		envpars[2] = new EfloyParam(1f,10f,1f,4f,"v0",false);
		envpars[3] = new EfloyParam(5f,50f,5f,10f,"sleep",false);
		envpars[4] = new EfloyParam(0f,50f,5f,30f,"margin",false);

		envpars[5] = new EfloyParam(0f,10f,1f,1f,"EnergyFactor",true);
		envpars[6] = new EfloyParam(0f,10f,1f,1f,"SafetyFactor",true);
		envpars[7] = new EfloyParam(0f,10f,1f,0f,"CooperationFactor",true);
		envpars[8] = new EfloyParam(0f,100f,10f,0f,"SurviversFactor",true);

 		envpars[9] = new EfloyParam(0f,20f,1f,10f,"MaxEnergyDose",true);
		envpars[10] = new EfloyParam(0f,20f,1f,10f,"MaxSafetyDose",true);
		envpars[11] = new EfloyParam(0f,20f,1f,10f,"MaxCooperationDose",true);

		envpars[12] = new EfloyParam(5f,50f,5f,15f,"PopulationSize",true);
		envpars[13] = new EfloyParam(0f,0.1f,0.01f,0.05f,"FreeWillFactor",true);
		envpars[14] = new EfloyParam(10f,200f,10f,50f,"LifeSpan",true);

	}


	static String EncodeChrom(EfloyParam pars[]) {

		int i;
		char kar;
		String st;
		StringBuffer sb = new StringBuffer(pars.length);

		for (i=0;i<pars.length;i++) {
			kar = pars[i].EncodeValue();
			sb.append(kar);
		}

		st = sb.toString();
		return st;

	}


	
	static public void reset() {

		NF = 15;
		KICK = (float) 0.05;
		SLEEP = 10;

	}
	

	private int GetFloyNumber(int fid) {

		int i, num;

		num = -1;
		for (i=0;i<Efloys.length;i++)
	 	{
			if (Efloys[i].id == fid)
				num = i;
		}
		return num;

	}

	public void ReleaseStranger() {

		int num;
                // Random generator = new Random();
 
                SortFloys(Efloys);  // ajd test added sort before food / stranger release.
		num = GetFloyNumber(0);
		Efloys[num].type = 0;
		Efloys[num].color = Color.green;
		// Efloys[num].energy = 20; //ajd Efloys[num].LifeSpan;
		// Efloys[num].safety = 10;
		// Efloys[num].cooperation = 10;
		Efloys[num].GetFitness();
		// Efloys[num].x = ( generator.nextFloat() * canvas.size().width );
                // Efloys[num].y = ( generator.nextFloat() * canvas.size().height );
                // Efloys[num].vx = ( generator.nextFloat() * Efloys[num].MaxSpeed );
                // Efloys[num].vy = ( generator.nextFloat() * Efloys[num].MaxSpeed );
		// Efloys[num].DistLocalFactor = -2;
		// Efloys[num].CollisionLocalFactor = -2; test ajd
                // was -40 AJD
		// Efloys[num].CenterAcceleration = 0.1f;

	}


	public boolean mouseEnter(Event evt, int x, int y) {

	
		if (evt.target == Start) {
			showStatus("Restart the Floys population with identical default values");
		}
		if (evt.target == Scramble) {
			showStatus("Scrambles the population with no food added");
		}
                if (evt.target == ScrambleAll) {
			showStatus("Scrambles the population with food 1 in 10 chance of green");
		}
		if (evt.target == Pause) {
			showStatus("Freeze all movement. A second click will resume movement");
		}
		if (evt.target == Evolution) {
			showStatus("Start/Stop a continuous process, where breeding occurs each time a stranger is killed");
		}
		if (evt.target == Rules) {
			showStatus("Display eFloys behavioral rules and the rules of the game");
		}
		if (evt.target == Info) {
			showStatus("Display Floys' traits, environmental properties and history data");
		}
		if (evt.target == Control) {
			showStatus("Modify individual or global properies - assign different colors to modified Floys");
		}
		if (evt.target == Slower) {
			showStatus("Define a slower Floy movement - click multiple time to get the desired effect");
		}
		if (evt.target == Faster) {
			showStatus("Define a faster Floy movement - click multiple time to get the desired effect");
		}
		if (evt.target == Stranger) {
			showStatus("Release a red intruder into the territory, and local Floys will chase and attack him");
		}
 		if (evt.target == Breed) {
			showStatus("Create a new generation, where each new Floy is the descendant of two old parents");
		}
 		if (evt.target == Help) {
			showStatus("Display help screen. Close the help screen by clicking its top right X icon");
		}
 		if (evt.target == Log) {
			showStatus("Display Log screen (log notes of current session). Close by clicking top right X icon");
		}
 		if (evt.target == Predefined) {
			showStatus("Select a set of predefined properties that define a specific environment and/or population");
		}

 		if (evt.target == Numbers) {
			showStatus("Toggle between display of Floys (fish like) shape and Floys identity numbers");
		}
 		if (evt.target == Sound) {
 			showStatus("Turn Off or On the sound effects (e.g. the sigh of a dying Floy)");
		}
 		if (evt.target == Ranges) {
 			showStatus("Limit/Reset variation ranges of properties during evolution");
		}
		
		return true;
	}


	public void Restart(boolean Reset) {

		int i;
		String st, fx, ev;

		if (Reset) {

			HistoryData.removeAllElements();

			InEvolution = false;
			NewGeneration = false;
			CurrentStep = 0;
			CurrentTotalStep = 0;
			CurrentGeneration = 0;

			ResetPopulation = true;
			stop();
			First = true;
			showStatus("");
			start();
		}

		if (ResetPopulation) {
			Efloys = new Efloy[NF];
			for (i=0;i<Efloys.length;i++) {
				CurrentNum = i;
				st = EncodeChrom(params);
				fx = EncodeChrom(fixpars);
				ev = EncodeChrom(envpars);
				Efloys[i] = new Efloy(params, st, fixpars, fx, envpars, ev);
				Efloys[i].id = i;
			}
			ResetPopulation = false;
		}

	}


	public void ResizeFrame(Frame frame) {

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int w = d.width;
		int h = d.height;
		frame.setVisible(false);
                frame.setBounds(0, 0, w, h); 
		frame.setVisible(true);

		/*
		int fw = 640;
		int fh = 480;
		int dw = (int) (w - fw)/2;
		int dh = (int) (h - fh)/2;
		frame.reshape(dw,dh,fw,fh);
		*/

	}


	public void CenterFrame(Frame frame) {

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int w = d.width;
		int h = d.height;
		int fw = 1280;
		int fh = 1080;
		int dw = (int) (w - fw)/2;
		int dh = (int) (h - fh)/2;  // AJD
		frame.setLocation(dw,dh);

	}


	void UpdateFloysParams() {

		int i;
		String st, fx, ev;

		int size = Efloys[0].PopulationSize;

		for (i=0;i<size;i++) {
			Efloys[i].params = params;
			Efloys[i].fixpars = fixpars;
			Efloys[i].envpars = envpars;
			st = EncodeChrom(params);
			fx = EncodeChrom(fixpars);
			ev = EncodeChrom(envpars);
			Efloys[i] = new Efloy(params, st, fixpars, fx, envpars, ev);
			Efloys[i].id = i;
		}
	}


	public boolean mouseExit(Event evt, int x, int y) {

		showStatus("");
		return true;

	}

	public boolean keyDown(Event evt, int key) {

		char c = (char) key;

		if (c == Event.F1) {
			helpwin.toFront();
			return true;
		}
		else
		if (c == Event.F2) {
			deb.toFront();
			return true;
		}
		else
		if (c == Event.F3) {
			deb.clear();
			return true;
		}
		else
		if (c == Event.F4) {
			InLog = false;
			return true;
		}
		else
		if (c == Event.F5) {
			InLog = true;;
			return true;
		}
		else
		if (c == Event.F6) {
			KickAll();
			return true;
		}
		else
			return false;

	}


	public boolean action(Event evt, Object o) {

		// int i,j;
		// Efloy Efloy;
		boolean rt = true;
		// String s1, s2, s3, s4;
		// long oldtime, newtime, timediff;

		if(evt.target == Quit) {
			showStatus("Quit");
			//destroy();
			//System.exit(0);
			try {
				MainPage = new URL("Javafloys.html");
				appcontext.showDocument(MainPage);
			}
			catch (MalformedURLException e){
			}
			rt=true;
		}

		else if (evt.target == Pause) {

			if (running) {
				runner.suspend();
				running = false;
				Pause.setLabel("Continue");
			}
			else {
				runner.resume();
				running = true;
				Pause.setLabel("Pause");
			}
			rt = true;
		}

		else if (evt.target == Evolution) {

			if (InEvolution) {
				Evolution.setLabel("Start Evolution");
				InEvolution = false;
				NewGeneration = false;
				showStatus("");
			}
			else {
				Evolution.setLabel("Stop Evolution");
				InEvolution = true;
				NewGeneration = true;
				showStatus("");
			}
			rt = true;

		}


		else if (evt.target == Info) {
			
			MainTitle.setForeground(Color.blue);
			String OldTitle = MainTitle.getText();
			MainTitle.setText("Loading Info Screen, please wait..");
			MainTitle.resize(MainTitle.preferredSize());

			showStatus("Loading Info Screen, please wait..");
			finfo = new EfloyInfo(Efloys);
			ResizeFrame(finfo);
			MainTitle.setText(OldTitle);
			MainTitle.setForeground(Color.black);


		}

		else if (evt.target == Control) {
			
			MainTitle.setForeground(Color.blue);
			String OldTitle = MainTitle.getText();
			MainTitle.setText("Loading Properties Screen, please wait..");
			MainTitle.resize(MainTitle.preferredSize());

			showStatus("Loading Control Screen, please wait..");
			fcommand = new EfloyCommand(Efloys);
			ResizeFrame(fcommand);
			MainTitle.setText(OldTitle);
			MainTitle.setForeground(Color.black);



		}

		else if (evt.target == Stranger) {
			
			ReleaseStranger();

		}

		else if (evt.target == Start) {
			
			if (!running) {
				runner.resume();
                                running = true;
				Pause.setLabel("Pause");
			}

			deb.clear();
			deb.start();
			InitParamsExtended(); // ajd test
			InEvolution = false;
			NewGeneration = false;
			CurrentStep = 0;
			CurrentTotalStep = 0;
			CurrentGeneration = 0;

			ResetPopulation = true;  // ajd-test
			Restart(true);

		}

		else if (evt.target == Scramble) {
			
			if (WithSound)
				beep.play();
			scramble(false);
                        canvas.repaint();

		}
                else if (evt.target == ScrambleAll) {

			if (WithSound)
				beep.play();
			scramble(true);
                        canvas.repaint();

		}
		else if (evt.target == Breed) {

			if (WithSound)
				beep.play();
                        deb.showMsg("New Generation #"+(CurrentGeneration+1)+" started at "+StartDate.toLocaleString());
			CreateNewGeneration();

		}


		else if (evt.target == Slower) {
			
			if (SLEEP < 10)
				SLEEP++;
			else if (SLEEP < 100)
				SLEEP += 10;
                        else if (SLEEP < 1000);
                                SLEEP += 100;
		}

		else if (evt.target == Faster) {
                    
                        if (SLEEP > 1000)
                                SLEEP -= 100;
                        else if (SLEEP > 100)
				SLEEP -= 10;
			else if (SLEEP > 1);
				SLEEP--;
		}

		else if (evt.target == Help) {
			//helpwin.toFront();
			helpwin.setVisible(true);
			rt = true;
		}

		else if (evt.target == Rules) {
			//rulewin.toFront();
			rulewin.setVisible(true);
			rt = true;
		}
		
		else if (evt.target == Log) {
			//deb.toFront();
			deb.setVisible(true);
			rt = true;
		}

		else if (evt.target == Predefined) {

			fpredefined = new EfloyPredefined();
			ResizeFrame(fpredefined);
			rt = true;

		}

		else if (evt.target == Numbers) {
			if (DrawNumbers) {
				Numbers.setLabel("Show Numbers");
				DrawNumbers = false;
			}
			else {
				Numbers.setLabel("Show Shapes");
				DrawNumbers = true;
			}
			//DrawNumbers = !DrawNumbers;
			rt = true;
		}

		else if (evt.target == Sound) {
			if (WithSound) {
				Sound.setLabel("Turn Sound On");
			}
			else {
				Sound.setLabel("Turn Sound Off");
			}
			WithSound = !WithSound;
			rt = true;
		}

		else if (evt.target == Ranges) {

			if (LimitedRanges) {
				Ranges.setLabel("Limited Ranges");
				InitParamsExtended();
				UpdateFloysParams();
				LimitedRanges = false;
			}
			else {
				Ranges.setLabel("Normal Ranges");
				InitParamsLimited();
				UpdateFloysParams();
				LimitedRanges = true;
			}
			rt = true;

			/*
			remove(canvas);
			add("Center",center);
			center.repaint();
			repaint();
			showStatus("Center");
			*/
		}

		else {
			rt = true;
		}


		//showStatus("evt.target= "+evt.target.toString());
		return rt;
	}


	public boolean handleEvent(Event evt) {

		if(evt.id == Event.WINDOW_DESTROY) {
			showStatus("Window Destroy");
			destroy();
			System.exit(0);
		}
		else
		if (evt.id == Event.MOUSE_DOWN) {
			//deb.toBack();
			deb.setVisible(false);
		}

		return super.handleEvent(evt);
	}




	void SortFloys() {

		int i,j;
		boolean swap;
		int e;
		Efloy etemp;

		for (i=0;i < Efloys.length-1;i++) {
			swap = false;
			for (j=Efloys.length-2;j>=i;j--) {
				Efloys[j].GetFitness();
				Efloys[j+1].GetFitness();
				e = Efloys[j].fitness - Efloys[j+1].fitness;
				if (e < 0) {
					etemp = Efloys[j];
					Efloys[j] = Efloys[j+1];
					Efloys[j+1] = etemp;
					swap = true;
				}
			}
			if (swap == false) {
				break;
			}
		}

	}


	void SortFloys(Efloy[] floys) {

		int i,j;
		boolean swap;
		int e;
		Efloy etemp;

		for (i=0;i < floys.length-1;i++) {
			swap = false;
			for (j=floys.length-2;j>=i;j--) {
				Efloys[j].GetFitness();
				Efloys[j+1].GetFitness();
				e = floys[j].fitness - floys[j+1].fitness;
				if (e < 0) {
					etemp = floys[j];
					floys[j] = floys[j+1];
					floys[j+1] = etemp;
					swap = true;
				}
			}
			if (swap == false) {
				break;
			}
		}

	}


	private long GetSumFitness() {

		int i;
		long sum;
		float eng, saf, avg;
		float facavg, facsaf;

		eng = 0;
		saf = 0;
		sum = 0;

		for (i=0;i < Efloys.length;i++) {
			eng = eng + (float) Efloys[i].energy;
			saf = saf + (float) Efloys[i].safety;
		}

		avg = 100;
		facavg = eng/Efloys.length/avg;
		facsaf =  saf/eng;

		for (i=0;i < Efloys.length;i++) {
			Efloys[i].energy = (int) (Efloys[i].energy/facavg);
			Efloys[i].safety = (int) (Efloys[i].safety/facavg/facsaf);
			Efloys[i].GetFitness();
			sum = sum + Efloys[i].fitness;
		}

		return sum;

	}

		

	private int GetParentID() {

		int i,j,k,n;
		int rand, partsum;

		i = 0;
		partsum = 0;
		//n = Efloys[0].PopulationSize;
		n = Efloys.length;
		rand =(int) (Math.random()*SumFitness);
		int avg = (int) (SumFitness/n);

		j = 0;
		for (i=0;i<Efloys.length;i++) {
			Efloys[i].GetFitness();
			partsum = partsum + (int) Efloys[i].fitness;
			if (partsum >= rand) {
				j = 1;
				break;
			}
		}

		/*
		while ((partsum < rand) && (i < n-1))  {
			partsum += (float) Efloys[i].fitness;
			i++;
		}
		*/
		
		if (j == 0) {
			i = Efloys.length-1;
		}

		//deb.showMsg("i= "+i+" j= "+j+" Sum= "+(int) SumFitness+" Avg = "+(int) avg+
		//	" Rand = "+(int) rand+" Part= "+partsum+" Fit= "+Efloys[i].fitness+" id = "+Efloys[i].id);

		return Efloys[i].id;	

	}


	private Efloy Mate() {

		int MomID, DadID, BreedTryMax, BreedTry ;
		Efloy Mom, Dad , Kid;
		String KidChrom, fx, ev;
		BreedTryMax = 10;
                BreedTry = 0;
		MomID = GetParentID();
		DadID = GetParentID();
		Mom = Efloys[GetFloyNumber(MomID)];
		Dad = Efloys[GetFloyNumber(DadID)];

		//if (Mom.type == 0) {
		//	while (Mom.type == 0) {
		//		MomID = GetParentID();
		//		Mom = Efloys[GetFloyNumber(MomID)];
			//	deb.showMsg("Repeating Mom = "+MomID);
		//	}
		//}

		//if (Dad.type == 0) {
		//	while (Dad.type == 0) {
		//		DadID = GetParentID();
		//		Dad = Efloys[GetFloyNumber(DadID)];
		//	//	deb.showMsg("Repeating Dad = "+DadID);
		//	}
		//}
                //
              if (Dad.color != Mom.color ) {
                  while  ( (BreedTry + 1) > BreedTryMax ) {
                        BreedTry = BreedTry++ ;
                        DadID = GetParentID();
                        Dad = Efloys[GetFloyNumber(DadID)];
                        if (Dad.color == Mom.color ) {
                            BreedTry = BreedTryMax;
                        }
                   }
              }

              if (Mom.type > 0 ) {
                if (Mom.color == Dad.color ) {
                    KidChrom = Dad.CrossOver(Mom.chrom);
                    fx = EncodeChrom(fixpars);
                    ev = EncodeChrom(envpars);
                    Kid = new Efloy(params, KidChrom, fixpars, fx, envpars, ev);
                    // Kid.chrom = Kid.mutate();
                    //if (Kid.color == Color.green)
                     	// Kid.AssignColor(Mom.GetColorNumber());
                    //  else
		    //	Kid.AssignColor(Dad.GetColorNumber());

                    /* Kid.energy = (int) ((Dad.energy + Mom.energy)/2);
                    Kid.safety = (int) ((Dad.safety + Mom.safety)/2);
                    // Dad.energy = (int) (Dad.energy - Dad.MaxEnergyDose );
                    Kid.cooperation = (int) ((Dad.cooperation + Mom.cooperation)/2); 
                     */
                    Kid.energy = (int) ((Dad.energy));
                    Kid.safety = (int) ((Mom.safety));
                    Kid.cooperation = (int) ((Mom.cooperation)); 
                     
                    Kid.GetFitness();
                    Kid.x = Mom.x;
                    Kid.y = Mom.y;
                }
                else {
                     Kid = Efloys[GetFloyNumber(MomID)];
                     // Kid.safety = (int) (Kid.safety - Kid.MaxSafetyDose);
                     // Kid.energy = (int) (Kid.energy + Kid.MaxEnergyDose );
                     Kid.x = Mom.x;
                     Kid.y = Mom.y;
                     Kid.vx = Mom.vx;
                     Kid.vy = Mom.vy;
                          
                }
              }else {
                    Kid = Efloys[GetFloyNumber(MomID)];
                     // Kid.safety = (int) (Kid.safety + Kid.MaxSafetyDose);
                     // Kid.energy = (int) (Kid.energy + Kid.MaxEnergyDose );
                    // Kid.DistLocalFactor = 1;
		    // Kid.CollisionLocalFactor = -1;
                     Kid.x = Mom.x;
                     Kid.y = Mom.y;
                     Kid.vx = Mom.vx;
                     Kid.vy = Mom.vy;
                }
                // canvas.repaint();  //  ajd

		/*
		deb.showMsg("---");
		deb.showMsg("SumFitness = "+SumFitness);
		deb.showMsg("Mom = "+MomID+": "+Mom.chrom+" Fit= "+Mom.fitness);
		deb.showMsg("Dad = "+DadID+": "+Dad.chrom+" Fit= "+Dad.fitness);
		deb.showMsg("Kid = "+KidChrom+" Kid.chrom= "+Kid.chrom);
		*/

		return Kid;

	}


	private void Breed() {

		int i,n;

		SumFitness = GetSumFitness();
		NewFloys = new Efloy[Efloys.length];

		for (i=0;i<Efloys.length;i++) {
			// deb.showMsg("Making kid #"+i);
			NewFloys[i] = Mate();
			NewFloys[i].id = i;
		}


		if (Efloys[0].SurviversFactor > 0) {
			SortFloys(Efloys);
			SortFloys(NewFloys);
			n = (int) (Efloys.length*Efloys[0].SurviversFactor/100);
			for (i=0;i<Efloys.length;i++) {
				if (i > (n-1))
					Efloys[i] = NewFloys[i-n];
			}
		}
		else {
			for (i=0;i<Efloys.length;i++)
					Efloys[i] = NewFloys[i];
		}

	}


	static boolean Flip(float ref) {

		float rand = (float) Math.random();
		if (rand < ref)
			return true;
		else
			return false;

	}



	private void PreScale() {
		
	
		int i;
		double tmin = 10e35;
		double tmax = -10e35;
		double tsum = 0;

		int size = Efloys[0].PopulationSize;
		for (i=0;i<size;i++) {
			Efloys[i].GetFitness();
			if (Efloys[i].fitness < tmin)
				tmin = Efloys[i].fitness;
			if (Efloys[i].fitness > tmax)
				tmax = Efloys[i].fitness;
			tsum = tsum + Efloys[i].fitness;
		}

		double tavg = tsum/size;
		// double temp = (tmax+tmin)/2;
		double factor = 1.80; //gadef.params.scale; (Default: 1.80)
		double a_coeff = 1;
		double b_coeff = 0;
		double delta;
		
		//deb.showMsg("tsum= "+tsum+" size = "+size+" tavg= "+tavg);
		//deb.showMsg("tmax= "+tmax+" tmin = "+tmin+" temp= "+temp);
		//deb.showMsg("factor= "+factor+" a = "+a_coeff+" b= "+b_coeff);

		if (factor > 1) {
				if (tmin > (factor * tavg - tmax) / (factor - 1)) {
					delta = tmax - tavg;
					if (delta != 0) {
						a_coeff = (factor - 1) * tavg / delta;
						b_coeff = tavg * (tmax - factor * tavg) / delta;
					}
				}
		}
		else {
				delta = tavg - tmin;
				if (delta != 0) {
					a_coeff = tavg / delta;
					b_coeff = -tmin * tavg / delta;
				}
		}

		//deb.showMsg("After. factor= "+factor+" a = "+a_coeff+" b= "+b_coeff);
		//deb.showMsg("Before 0 = "+Efloys[0].fitness+" 1= "+Efloys[1].fitness);

		for (i=0;i<size;i++)
			Efloys[i].fitness = (int) (Efloys[i].fitness*a_coeff + b_coeff);

		//deb.showMsg("After 0 = "+Efloys[0].fitness+" 1= "+Efloys[1].fitness);
		//deb.showMsg(" ");

		//SortFloys();
		
	}

	
	void CreateHelp(EfloyLog win) {

		int i;
		String txt;

		win.ta.setForeground(Color.black);
		win.ta.setFont(new Font("TimesRoman",Font.BOLD,12));
		win.clear();

		txt = "";

		txt = txt + "Contents\n\n";
		txt = txt + "1.\t Button Functions\n";
		txt = txt + "2.\t Properties\n";
		txt = txt + "3.\t Keyboard Functions\n";
		txt = txt + "4.\t Differences between Netscape and Microsoft Internet Explorer\n";
		
		txt = txt + "\n\n";

		txt = txt + "Button Functions - Bottom Button Bar\n\n";

		txt = txt + "Restart:\t\t Restart the Floys population with identical default values\n";
		txt = txt + "Scramble:\t\t Give a high dose of radiation, causing many mutations and diverse population\n";
		txt = txt + "Breed:\t\t Create a new generation, where each new Floy is the descendant of two old parents\n";
		txt = txt + "Evolution:\t\t Start/Stop a continuous process, where breeding occurs each time a stranger is killed\n";
		txt = txt + "Insert Stranger:\t Release a red intruder into the territory, and local Floys will chase and attack him\n";

		txt = txt + "\nButton Functions - Right Button Bar\n\n";

		txt = txt + "Show Help:\t Display help screen. Close the help screen by clicking its top right X icon\n";
		txt = txt + "Show Log Screen:\t Display Log screen (log notes of current session). Close by clicking top right X icon\n";
		txt = txt + "Predefined:\t\t Select a set of predefined properties that define a specific environment and/or population\n";
		txt = txt + "Show Rules:\t\t Display eFloys behavioral rules, and the rules of the game\n";
		txt = txt + "Show Info:\t Display Floys' traits, environmental properties and history data\n";
		txt = txt + "Edit Properties:\t Modify individual or global properies - assign different colors to modified Floys\n";
		
		
		txt = txt + "\nButton Functions - Left Button Bar\n\n";

		txt = txt + "Show Numbers:\t Toggle between display of Floys (fish like) shape and Floys identity numbers\n";
		txt = txt + "Turn Sound Off:\t Turn Off or On the sound effects (e.g. the sigh of a dying Floy)\n";
		txt = txt + "Slower:\t\t Define a slower Floy movement - click multiple time to get the desired effect\n";
		txt = txt + "Faster:\t\t Define a faster Floy movement - click multiple time to get the desired effect\n";
		txt = txt + "Pause:\t\t Freeze all movement. A second click will resume movement\n";
		txt = txt + "Limited Ranges:\t\t Limit/Reset variation ranges for critical properties\n";
		

		txt = txt + "\n\nDescription of Properties (Units are arbitrary and have no meaning)\n\n";

		txt = txt + "Primary Traits:\t The main behavioral traits, affected by evolutionary operators (crossover and mutation)\n";
		txt = txt + "Secondary Traits:\t The specific responses to various inputs, affected by the evolutionary operators\n";
		txt = txt + "Fixed traits:\t Various individual traits, not affected by the evolutionary operators\n";
		txt = txt + "Environmental:\t These are properties of the environment, and are shared by the whole community\n";
		txt = txt + "\n";

		txt = txt + "Primary Traits\n\n";
		txt = txt + "Maximum Speed:\t The maximum speed a Floy can reach\n";
		txt = txt + "Bounce Speed:\t The speed of turning away when hitting a wall\n";
		txt = txt + "Acceleration:\t The basic acceleration of response to various inputs\n";
		txt = txt + "Attract to Center:\t The force by which a Floy is attracted to the center of the territory\n";
		txt = txt + "Collision Distance:\t The distance (squared) at which a Floy can touch another\n";
		txt = txt + "\n";


		txt = txt + "Secondary Traits:\n\n";

		txt = txt + "Response (speed change) to a distant Floy\n";
		txt = txt + "Local to Local:\t A Floy's response to a member of its own community\n";
		txt = txt + "Local to Stranger:\t How a Floy in his own territory responds to a foreigner\n";
		txt = txt + "Stranger to Local:\t How a Floy not in his own territory responds to local Floys\n";
		txt = txt + "\n";

		txt = txt + "Response (speed change) to a close Floy (at touching distance)\n";
		txt = txt + "Local to Local:\t A Floy's response to a member of its own community\n";
		txt = txt + "Local to Stranger:\t How a Floy in his own territory responds to a foreigner\n";
		txt = txt + "Stranger to Local:\t How a Floy not in his own territory responds to local Floys\n";
		txt = txt + "\n";

		txt = txt + "Fixed Traits\n\n";
		txt = txt + "Type:\t\t Either local or stranger. By default, any Floy with a red color is a stranger\n";
		txt = txt + "Color:\t\t The Floy's color, useful for observing and following specific Floys\n";
		txt = txt + "No. of Neighbors:\t The number of Floys to relate to\n";
		txt = txt + "Mutation Factor:\t The probability for a gene to be changed by mutation\n";
		txt = txt + "Crossover Factor:\t The probability for crossover in the breeding process (in general should be 1)\n";
		txt = txt + "Energy Level:\t A dynamic attribute, affected by interaction with strangers\n";
		txt = txt + "Safety Level:\t A dynamic attribute, affected by interaction with one's neighbors\n";
		txt = txt + "Cooperation Level:\t A previous-generation-dependent attribute, affected by community achievements\n";
		txt = txt + "Fitness Level:\t Overall fitness calculated as a weighted function of the above\n";
		txt = txt + "\n";

		txt = txt + "Environmental Properties\n\n";
		txt = txt + "Population Size:\t The number of Floys in the community\n";
		txt = txt + "Slowdown Factor:\t A delay factor for adjusting the overall movement speed\n";
		txt = txt + "Energy Factor:\t The weighting factor of energy in the fitness function\n";
		txt = txt + "Safety Factor:\t The weighting factor of safety in the fitness function\n";
		txt = txt + "Cooperation Factor:\t The weighting factor of cooperation in the fitness function\n";
		txt = txt + "Max. Energy Dose:\t Maximum energy points (reward or penalty) an eFloy can get or lose\n";
		txt = txt + "Max. Safety Dose:\t Maximum safety points (reward or penalty) an eFloy can get or lose\n";
		txt = txt + "Max. Coop, Dose:\t Maximum cooperation points (reward or penalty) an eFloy can get or lose\n";
		txt = txt + "Survivers Percent:\t The percentage of parents that are allowed to continue in the new generation\n";
		txt = txt + "Free Will Factor:\t The amount of randomal behavior \n";
		txt = txt + "Stranger Life Span:\t The energy given to a stranger at birth, defining its life span.\n";


		txt = txt + "\n\nKeyboard functions\n";
		txt = txt + "Available when the applet has focus: Click anywhere in the applet area to give it focus\n\n";

		txt = txt + "F1:\t Display help screen (this screen)\n";
		txt = txt + "F2:\t Display Log Screen\n";
		txt = txt + "F3:\t Reset Log Screen (Clear log file)\n";
		txt = txt + "F4:\t Stop Log (Stop writing to log file)\n";
		txt = txt + "F5:\t Restart Log (Resume writing to log file)\n";
		txt = txt + "F6:\t Give a gentle kick (a gentle mutation, unlike the Scramble)\n";


		txt = txt + "\n\nDifferences between Netscape and Microsoft Internet Explorer\n\n";

		txt = txt + "Netscape 3.0 and Explorer 3.0 browsers (for Windows 95) behave differently in some respects\n";
		txt = txt + "Here are a few points that I noticed, and are relevant to the Floys applet\n";
		txt = txt + "\n";
		txt = txt + "1. The applet runs faster on Explorer. Adjust by the Slower and Faster buttons (clicking several times)\n";
		txt = txt + "2. Context sensitive help for each button is displayed in Netscape's status bar but not in Explorer's\n";
		txt = txt + "3. Scrollbars (in the Info screen) behave differrently in the two browsers\n";
		txt = txt + "4. The Help and Log screens are closed in Netscape, but not in Explorer. You can minimize them instead\n";
		txt = txt + "5. Colored labels are displayed colored in Netscape, black in Explorer\n";
		txt = txt + "\n";

		win.showMsg(txt);

	}


	void CreateRules(EfloyLog win) {

		int i;
		String txt;

		win.ta.setForeground(Color.black);
		win.ta.setFont(new Font("TimesRoman",Font.BOLD,12));
		win.clear();

		txt = "";
		txt = txt + "The instinctive behavioral rules of eFloys\n";
		txt = txt + "\n";

		txt = txt + "eFloys' behavior is governed by two rules:\n";
		txt = txt + "\t1. A rule specifying how to relate to one's own kind.\n";
		txt = txt + "\t2. A rule specifying how to relate to strangers.\n";
		txt = txt + "\n";
		txt = txt + "1. How to relate to one's own kind:\n";
		txt = txt + "\tIdentify two members of your flock that are near to you and try to stay close to them, but not too close.\n";
		txt = txt + "\n";

		txt = txt + "2. How to relate to strangers:\n";
		txt = txt + "\tIf you are in your territory:\n"; 
		txt = txt + "\t\tWhen you spot a stranger go after him, if you are close enough - attack\n";
		txt = txt + "\tIf you are not in your territory:\n";
		txt = txt + "\t\tIf local Floys chase you - run away.\n";
		txt = txt + "\n\n";

		txt = txt + "Rewards and Penalties\n";
		txt = txt + "(The fitness is calculated as a weighted function of total energy and safety)\n";
		txt = txt + "\n";

		txt = txt + "Biting a stranger:\t\t\t Energy increase\n";
		txt = txt + "Moving fast:\t\t\t Energy decrease\n";
		txt = txt + "Being bitten by a local eFloy:\t\t Energy decrease\n";
		txt = txt + "Keeping close to one's neightbors:\t Safety increase\n";
		txt = txt + "\n\n";

		txt = txt + "How to play with eFloys\n";
		txt = txt + "\n";

		txt = txt + "1. Adjust overall movement speed (if necessary)\n";
		txt = txt + "\tThe default movement should be smooth and calm\n";
		txt = txt + "\tClick the 'Slower' and/or 'Faster' buttons several times to define a smooth movement\n";
		txt = txt + "\n";

		txt = txt + "2. Play with Population\n";
		txt = txt + "\n";

		txt = txt + "\tInsert a stranger and watch how eFloys chase and attack him\n";
		txt = txt + "\tThe poor stranger's sigh means he is finally eliminated\n";
		txt = txt + "\n";
		txt = txt + "\tGo to 'Edit Properties' and modify the environment or the traits\n";
		txt = txt + "\tClick 'UpdateAll' to apply the new traits to all members of the population\n";
		txt = txt + "\t(Optionally) Insert a stranger\n";
		txt = txt + "\n";
		txt = txt + "\tGo to 'Edit Properties' and assign traits to a specific eFloy (or eFloys)\n";
		txt = txt + "\tRemember to assign each modified eFloy a different color, so that he will be easy to follow\n";
		txt = txt + "\tClick 'Update' for each eFloy.\n";
		txt = txt + "\t(Optionally) Insert a stranger\n";
		txt = txt + "\n";
		txt = txt + "\n";



		txt = txt + "3. Play with Evolution\n";
		txt = txt + "\n";

		txt = txt + "\tDefine the fitness function\n";
		txt = txt + "\tGo to 'Edit Properties' and assign Energy and Safety Weight Factors\n";
		txt = txt + "\tIf Energy weight is higher, eFloys will evolve to be braver and faster\n";
		txt = txt + "\tIf Safety weight is higher, eFloys will evolve to be more cautious and they will keep together\n";
		txt = txt + "\n";

		txt = txt + "\tStart with a diverse, random population\n";
		txt = txt + "\tClick 'Scramble' several times. Each click will make the population more diverse\n";
		txt = txt + "\tClick 'Start Evolution'\n";
		txt = txt + "\n";

		txt = txt + "\tor Start with a homogeneous population\n";
		txt = txt + "\tUse default population, or one you defines by 'UpdateAll' in 'Edit Properties'\n";
		txt = txt + "\tIn this case evolution will be slower, because it can build only on mutation\n";
		txt = txt + "\tClick 'Start Evolution'\n";
		txt = txt + "\n";

		txt = txt + "\tor Start with controlled population\n";
		txt = txt + "\tUse a population that you defined previously by assigning each eFloy his own specific traits\n";
		txt = txt + "\tClick 'Start Evolution'\n";
		txt = txt + "\n";
		txt = txt + "\n";

		txt = txt + "4. How to use the buttons\n";
		txt = txt + "\tClick on any button and see what it does\n";
		txt = txt + "\tor look in the Help screen (click 'Show Help')\n";
		txt = txt + "\n";

		win.showMsg(txt);

	}



	void KickAll() {

		int i;

		for (i=0;i<Efloys.length;i++) {
			Efloys[i].mutate();
		}

		beep.play();

	}

}




