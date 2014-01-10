//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//

import java.awt.*;

final class EfloyCommand extends Frame {

    Button Environ;
    Button Fixed;
    Button Variable;
	Button Secondary;
	Button Test;
	Button Reset;
	Button Update;
	Button Updateall;
	Button Quit;
	
	Panel ButtonPanel;
	Panel TitlePanel;
	Panel InputPanel;
	Panel EnvironPanel;
	Panel FixedPanel;
	Panel VariablePanel;
	Panel SecondaryPanel;
	Panel TestPanel;

	Label TitleLabel;
	CardLayout cardLayout;

	Label EnvLabels[];
	Choice[] EnvChoices;
	Label[] EnvDummiesA;
	Label[] EnvDummiesB;
	Label[] EnvDummiesC;

	Label FixLabels[];
	Choice[] FixChoices;
	Label[] FixDummiesA;
	Label[] FixDummiesB;
	Label[] FixDummiesC;

	Label VarLabels[];
	Choice[] VarChoices;
	Label[] VarDummiesA;
	Label[] VarDummiesB;
	Label[] VarDummiesC;

	Label SecLabels[];
	Choice[] SecChoices;
	Label[] SecDummiesA;
	Label[] SecDummiesB;
	Label[] SecDummiesC;

	Choice Floynum;
	Label FloynumLabel;

    Efloy floys[];
	int SelectedFloy;
	Font ButtonFont;

	int CurNF;
	int CurPop;
    
    public EfloyCommand (Efloy f[]) {
        super("Control Center");
        floys = f;
		float fi = 0f;
		int i,j;
        
		ButtonFont = new Font("TimesRoman",Font.PLAIN,12);
		setBackground(Color.white);
		setForeground(Color.black);
		InitControls();

        Environ = new Button("Environment");
        Fixed = new Button("Fixed Traits");
        Variable = new Button("Primary Traits");
		Secondary = new Button("Secondary Traits");
		//Test = new Button("Test");
		//Reset = new Button("Reset");
		Update = new Button("Update");
		Updateall = new Button("Update All");
		Quit = new Button("Quit");

		Floynum = new Choice();
		FillFloyNumbers(Floynum);

        ButtonPanel = new Panel();
        ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        ButtonPanel.add(Environ);
        ButtonPanel.add(Fixed);
        ButtonPanel.add(Variable);
		ButtonPanel.add(Secondary);
		//ButtonPanel.add(Test);
		//ButtonPanel.add(Reset);
		ButtonPanel.add(Update);
		ButtonPanel.add(Updateall);
		ButtonPanel.add(Quit);
		ButtonPanel.setFont(ButtonFont);
        this.add("South", ButtonPanel);

        TitlePanel = new Panel();
        TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		TitleLabel = new Label("Environmental Properties");
		TitlePanel.add(TitleLabel);
		FloynumLabel = new Label("Floy Number:");
		TitlePanel.add(FloynumLabel);
		TitlePanel.add(Floynum);
		this.add("North",TitlePanel);
		Font TitleFont = new Font("TimesRoman",Font.BOLD,14);
		TitlePanel.setFont(TitleFont);
		TitlePanel.setForeground(Color.blue);
		UpdateTitle("              Environmental Properties           ", true);
		//UpdateTitle("Environmental Properties", true);
		//UpdateTitle("Environmental Properties", false);
		//UpdateTitle("Primary Traits", true);
		//cardLayout.show(InputPanel,"Variable");

        InputPanel = new Panel();
		cardLayout = new CardLayout(0,0);
		InputPanel.setLayout(cardLayout);

		EnvironPanel = new Panel();
		InitEnviron();
		InputPanel.add("Environ",EnvironPanel);

		FixedPanel = new Panel();
		InitFixed();
		InputPanel.add("Fixed",FixedPanel);

		VariablePanel = new Panel();
		InitVariable();
		InputPanel.add("Variable",VariablePanel);

		SecondaryPanel = new Panel();
		InitSecondary();
		InputPanel.add("Secondary",SecondaryPanel);

		TestPanel = new Panel();
        TestPanel.setLayout(new GridLayout(floys.length+2,9));
		InputPanel.add("Test",TestPanel);

		this.add("Center",InputPanel);
		//Efloys.appcontext.showStatus("Loading Control Screen");

		this.pack();
		this.hide();
		UpdateTitle("Environmental Properties", false);

	}


	private void InitControls() {

		int i, j;

		EnvLabels = new Label[11];
		EnvChoices = new Choice[11];
		EnvDummiesA = new Label[11];
		EnvDummiesB = new Label[11];
		EnvDummiesC = new Label[11];

		FixLabels = new Label[11];
		FixChoices = new Choice[11];
		FixDummiesA = new Label[11];
		FixDummiesB = new Label[11];
		FixDummiesC = new Label[11];

		VarLabels = new Label[11];
		VarChoices = new Choice[11];
		VarDummiesA = new Label[11];
		VarDummiesB = new Label[11];
		VarDummiesC = new Label[11];

		SecLabels = new Label[11];
		SecChoices = new Choice[11];
		SecDummiesA = new Label[11];
		SecDummiesB = new Label[11];
		SecDummiesC = new Label[11];

		Font font = new Font("TimesRoman",Font.BOLD,12);

		for (i=0;i<11;i++) {
			EnvLabels[i] = new Label("");
			EnvChoices[i] = new Choice();
			EnvDummiesA[i] = new Label("");
			EnvDummiesB[i] = new Label("");
			EnvDummiesC[i] = new Label("");
			EnvLabels[i].setFont(font);

			FixLabels[i] = new Label("");
			FixChoices[i] = new Choice();
			FixDummiesA[i] = new Label("");
			FixDummiesB[i] = new Label("");
			FixDummiesC[i] = new Label("");
			FixLabels[i].setFont(font);

			VarLabels[i] = new Label("");
			VarChoices[i] = new Choice();
			VarDummiesA[i] = new Label("");
			VarDummiesB[i] = new Label("");
			VarDummiesC[i] = new Label("");
			VarLabels[i].setFont(font);

			SecLabels[i] = new Label("");
			SecChoices[i] = new Choice();
			SecDummiesA[i] = new Label("");
			SecDummiesB[i] = new Label("");
			SecDummiesC[i] = new Label("");
			SecLabels[i].setFont(font);

		}

	}
	

	private void InitInputPanel(Panel panel, Label[] labels, Choice[] choices, Label[] dummiesa, Label[] dummiesb, Label[] dummiesc, int num) {

		int i;

		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;

		for (i=0;i<11;i++) {
 			constraints.gridwidth = 1;
			layout.setConstraints(dummiesa[i], constraints);
			panel.add(dummiesa[i]);

			constraints.gridwidth = 2;
			layout.setConstraints(labels[i], constraints);
			panel.add(labels[i]);

			constraints.gridwidth = 1;
			if (i > num-1) {
				layout.setConstraints(dummiesc[i], constraints);
				panel.add(dummiesc[i]);
			}
			else {
				layout.setConstraints(choices[i], constraints);
				panel.add(choices[i]);
			}


			constraints.gridwidth = GridBagConstraints.REMAINDER;
			layout.setConstraints(dummiesb[i], constraints);
			panel.add(dummiesb[i]);

		}

		panel.resize(panel.preferredSize());

	}



	private void InitVariable() {

		int i;

		VarLabels[0].setText("Maximum Speed");
		VarLabels[1].setText("Bounce-from-Wall Speed");
		VarLabels[2].setText("Acceleration");
		VarLabels[3].setText("Attraction to Center");
		VarLabels[4].setText("Collision Distance");

		InitChoice(VarChoices[0],Efloys.params, 0);
		InitChoice(VarChoices[1],Efloys.params, 1);
		InitChoice(VarChoices[2],Efloys.params, 2);
		InitChoice(VarChoices[3],Efloys.params, 4);
		InitChoice(VarChoices[4],Efloys.params, 8);

		InitInputPanel(VariablePanel, VarLabels, VarChoices, VarDummiesA, VarDummiesB, VarDummiesC, 5);

	}

	

	private void InitSecondary() {

		int i;

		SecLabels[0].setText("Distance Response (Local to Local)");
		SecLabels[1].setText("Distance Response (Local to Stranger)");
		SecLabels[2].setText("Distance Response (Stranger to Local)");
		SecLabels[3].setText("Collision Response (Local to Local)");
		SecLabels[4].setText("Collision Response (Local to Stranger)");
		SecLabels[5].setText("Collision Response (Stranger to Local)");

		InitChoice(SecChoices[0],Efloys.params, 5);
		InitChoice(SecChoices[1],Efloys.params, 6);
		InitChoice(SecChoices[2],Efloys.params, 7);
		InitChoice(SecChoices[3],Efloys.params, 9);
		InitChoice(SecChoices[4],Efloys.params, 10);
		InitChoice(SecChoices[5],Efloys.params, 11);

		InitInputPanel(SecondaryPanel, SecLabels, SecChoices, SecDummiesA, SecDummiesB, SecDummiesC, 6);

	}



	private void InitEnviron() {

		int i;

		CurNF = Efloys.NF;
		CurPop = floys[0].PopulationSize;

		EnvLabels[0].setText("Population Size");
		EnvLabels[1].setText("Movement Slowdown Attribute");
		EnvLabels[2].setText("Energy Weight factor");
		EnvLabels[3].setText("Safety Weight Factor");
		EnvLabels[4].setText("Cooperation Weight Factor");
		EnvLabels[5].setText("Max. Energy Dose");
		EnvLabels[6].setText("Max. Safety Dose");
		EnvLabels[7].setText("Max. Cooperation Dose");
		EnvLabels[8].setText("Survivers Percent");
		EnvLabels[9].setText("Free Will Factor");
		EnvLabels[10].setText("Stranger Life Span");


		InitChoice(EnvChoices[0],Efloys.envpars, 12);
		InitChoice(EnvChoices[1],Efloys.envpars, 3);
		InitChoice(EnvChoices[2],Efloys.envpars, 5);
		InitChoice(EnvChoices[3],Efloys.envpars, 6);
		InitChoice(EnvChoices[4],Efloys.envpars, 7);
		InitChoice(EnvChoices[5],Efloys.envpars, 9);
		InitChoice(EnvChoices[6],Efloys.envpars, 10);
		InitChoice(EnvChoices[7],Efloys.envpars, 11);
		InitChoice(EnvChoices[8],Efloys.envpars, 8);
		InitChoice(EnvChoices[9],Efloys.envpars, 13);
		InitChoice(EnvChoices[10],Efloys.envpars, 14);

		InitInputPanel(EnvironPanel, EnvLabels, EnvChoices, EnvDummiesA, EnvDummiesB, EnvDummiesC, 11);

	}


	private void InitFixed() {

		int i;

		FixLabels[0].setText("Type");
		FixLabels[1].setText("Color");
		FixLabels[2].setText("Number of Neighbors");
		FixLabels[3].setText("Mutation Factor");
		FixLabels[4].setText("Crossover Factor");
		FixLabels[5].setText("Energy Level");
		FixLabels[6].setText("Safety Level");
		FixLabels[7].setText("Cooperation Level");
		FixLabels[8].setText("Fitness");

		FillTypes(FixChoices[0]);
		FillColors(FixChoices[1]);
		InitChoice(FixChoices[2],Efloys.fixpars, 5);
		InitChoice(FixChoices[3],Efloys.fixpars, 6);
		InitChoice(FixChoices[4],Efloys.fixpars, 7);
		InitChoice(FixChoices[5],Efloys.fixpars, 8);
		InitChoice(FixChoices[6],Efloys.fixpars, 9);
		InitChoice(FixChoices[7],Efloys.fixpars, 10);
		InitChoice(FixChoices[8],Efloys.fixpars, 11);
		
		InitInputPanel(FixedPanel, FixLabels, FixChoices, FixDummiesA, FixDummiesB, FixDummiesC, 9);

	}


	private void InitChoice(Choice choice, EfloyParam param[], int Parnum) {

		float fi;

        for (int i = 0; i <= param[Parnum].nsteps; i++) {
			fi = (float) (param[Parnum].min + i*param[Parnum].step);
            choice.addItem(Float.toString(fi));
		}
        choice.select(Float.toString((float) param[Parnum].value));

	}



	private void FillColors(Choice choice) {

		choice.addItem("BLACK");
		choice.addItem("BLUE");
		choice.addItem("CYAN");
		choice.addItem("DARKGRAY");
		choice.addItem("GRAY");
		choice.addItem("GREEN");
		choice.addItem("LIGHTGRAY");
		choice.addItem("MAGENTA");
        choice.addItem("ORANGE");
		choice.addItem("PINK");
		choice.addItem("RED");
		choice.addItem("WHITE");
		choice.addItem("YELLOW");

		choice.select(floys[floys.length-1].GetColorName());
		//choice.select("GREEN");

	}


	private void FillTypes(Choice choice) {

		choice.addItem("Local");
		choice.addItem("Stranger");

		choice.select(floys[floys.length-1].GetTypeName());
		//choice.select("Local");

	}

	void FillFloyNumbers(Choice choice) {

		int i;

		for (i=0;i<floys.length;i++) {
			choice.addItem(""+(i+1));
		}
		choice.select("1");

	}


	void SortFloys() {

		int i,j;
		boolean swap;
		int e;
		Efloy etemp;

		for (i=0;i < floys.length-1;i++) {
			swap = false;
			for (j=floys.length-2;j>=i;j--) {
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


	private int GetFloyNumber(int fid) {

		int i, num;

		num = -1;
		for (i=0;i<Efloys.Efloys.length;i++)
	 	{
			if (Efloys.Efloys[i].id == fid)
				num= i;
		}
		return num;

	}


	int GetSelectedFloy(Choice floynum) {

		String fnum;
		int fid, num;

		fid = -1;

		fnum = readString(floynum, "1");
		if (fnum.equals("Stranger"))
			fid = 0;
		else
			fid = Integer.parseInt(fnum);

		if (fid > -1) 
			num = GetFloyNumber(fid);
		else
			num = -1;

		return num;


	}


	private void UpdateControls(Choice floynum) {

		String fnum;
		int fid, num;

		fnum = readString(floynum, "1");
		if (fnum.equals("Stranger"))
			fid = 0;
		else
			fid = Integer.parseInt(fnum);

		num = GetFloyNumber(fid);

		VarChoices[0].select(Float.toString((float) floys[num].MaxSpeed));
		VarChoices[1].select(Float.toString((float) floys[num].BounceSpeed));
		VarChoices[2].select(Float.toString((float) floys[num].ApproachAcceleration));
		VarChoices[3].select(Float.toString((float) floys[num].CenterAcceleration));
		VarChoices[4].select(Float.toString((float) floys[num].CollisionDistance));

		SecChoices[0].select(Float.toString((float) floys[num].DistBrotherFactor));
		SecChoices[1].select(Float.toString((float) floys[num].DistStrangerFactor));
		SecChoices[2].select(Float.toString((float) floys[num].DistLocalFactor));
		SecChoices[3].select(Float.toString((float) floys[num].CollisionBrotherFactor));
		SecChoices[4].select(Float.toString((float) floys[num].CollisionStrangerFactor));
		SecChoices[5].select(Float.toString((float) floys[num].CollisionLocalFactor));
		
		FixChoices[0].select(floys[num].GetTypeName());
		FixChoices[1].select(floys[num].GetColorName());
		FixChoices[2].select(Float.toString((int) floys[num].NumberOfNeighbors));
		FixChoices[3].select(Float.toString((int) floys[num].MutationFactor));
		FixChoices[4].select(Integer.toString((int) floys[num].CrossoverFactor));
		FixChoices[5].select(Integer.toString((int) floys[num].energy));
		FixChoices[6].select(Integer.toString((int) floys[num].safety));
		FixChoices[7].select(Integer.toString((int) floys[num].cooperation));
		FixChoices[8].select(Integer.toString((int) floys[num].fitness));

		EnvChoices[0].select(Integer.toString((int) floys[num].PopulationSize));
		EnvChoices[1].select(Integer.toString((int) floys[num].sleep));
		EnvChoices[2].select(Float.toString((float) floys[num].EnergyFactor));
		EnvChoices[3].select(Float.toString((float) floys[num].SafetyFactor));
		EnvChoices[4].select(Float.toString((float) floys[num].CooperationFactor));
		EnvChoices[5].select(Float.toString((float) floys[num].MaxEnergyDose));
		EnvChoices[6].select(Float.toString((float) floys[num].MaxSafetyDose));
		EnvChoices[7].select(Float.toString((float) floys[num].MaxCooperationDose));
		EnvChoices[8].select(Float.toString((float) floys[num].FreeWillFactor));
		
	}



	private String readString(Choice c, String d) {
        String n;
        
        try {
            n = c.getSelectedItem();
            }
        catch (Exception e) {
            n = d;
            }
        
        return n;
    }


    private int readInt(Choice c, int d) {
        int n;
        
        try {
            n = Integer.parseInt(c.getSelectedItem());
            }
        catch (NumberFormatException e) {
            n = d;
            }
        
        return n;
    }


    private float readFloat(Choice c, float d) {
        float n;
        
        try {
            n = Float.valueOf(c.getSelectedItem()).floatValue();
            }
        catch (NumberFormatException e) {
            n = d;
            }
        
        return n;
   }



    private Color readColor(Choice c, String d) {
        String n;
		Color col;
        
        try {
            n = c.getSelectedItem();
            }
        catch (Exception e) {
            n = d;
            }
        
		col = GetColorByString(n);
        return col;

    }


	public Color GetColorByString(String c) {

		Color col;

		if (c.equals("BLACK")) col = Color.black;
		else if (c.equals("BLUE")) col = Color.blue;
		else if (c.equals("CYAN")) col = Color.cyan;
		else if (c.equals("DARKGRAY")) col = Color.darkGray;
		else if (c.equals("GRAY")) col = Color.gray;
		else if (c.equals("GREEN")) col = Color.green;
		else if (c.equals("LIGHTGRAY")) col = Color.lightGray;
		else if (c.equals("ORANGE")) col = Color.orange;
		else if (c.equals("PINK")) col = Color.pink;
		else if (c.equals("RED")) col = Color.red;
		else if (c.equals("WHITE")) col = Color.white;
		else if (c.equals("YELLOW")) col = Color.yellow;
		else col = Color.red;

		return col;

	}


	private void UpdateVariable(int num) {

		Efloys.params[0].value = readFloat(VarChoices[0], floys[num].MaxSpeed);
		Efloys.params[1].value = readFloat(VarChoices[1], floys[num].BounceSpeed);
		Efloys.params[2].value = readFloat(VarChoices[2], floys[num].ApproachAcceleration);
		Efloys.params[4].value = readFloat(VarChoices[3], floys[num].CenterAcceleration);
		Efloys.params[8].value = readInt(VarChoices[4], floys[num].CollisionDistance);

	}


	private void UpdateSecondary(int num) {

		Efloys.params[5].value = readFloat(SecChoices[0], floys[num].DistBrotherFactor);
		Efloys.params[6].value = readFloat(SecChoices[1], floys[num].DistStrangerFactor);
		Efloys.params[7].value = readFloat(SecChoices[2], floys[num].DistLocalFactor);
		Efloys.params[9].value = readFloat(SecChoices[3], floys[num].CollisionBrotherFactor);
		Efloys.params[10].value = readFloat(SecChoices[4], floys[num].CollisionStrangerFactor);
		Efloys.params[11].value = readFloat(SecChoices[5], floys[num].CollisionLocalFactor);

	}


	private void UpdateFixed(int num) {

		//Efloys.fixpars[3].value = readInt(FixChoices[0], floys[num].type);
		Efloys.fixpars[3].value = FixChoices[0].getSelectedIndex();
		Efloys.fixpars[4].value = FixChoices[1].getSelectedIndex();
		/*
		if (Efloys.fixpars[4].value == 10)
				Efloys.fixpars[3].value = 1;
		else
				Efloys.fixpars[3].value = 0;
		*/

		Efloys.fixpars[5].value = readInt(FixChoices[2], floys[num].NumberOfNeighbors);
		Efloys.fixpars[6].value = readFloat(FixChoices[3], floys[num].MutationFactor);
		Efloys.fixpars[7].value = readFloat(FixChoices[4], floys[num].CrossoverFactor);
		Efloys.fixpars[8].value = readInt(FixChoices[5], floys[num].energy);
		Efloys.fixpars[9].value = readInt(FixChoices[6], floys[num].safety);
		Efloys.fixpars[10].value = readInt(FixChoices[7], floys[num].cooperation);
		Efloys.fixpars[11].value = readInt(FixChoices[8], floys[num].fitness);

	}


	private void UpdateEnviron(int num) {

		Efloys.envpars[12].value = readInt(EnvChoices[0], floys[num].PopulationSize);
		Efloys.envpars[3].value = readInt(EnvChoices[1], floys[num].sleep);
		Efloys.envpars[5].value = readFloat(EnvChoices[2], floys[num].EnergyFactor);
		Efloys.envpars[6].value = readFloat(EnvChoices[3], floys[num].SafetyFactor);
		Efloys.envpars[7].value = readFloat(EnvChoices[4], floys[num].CooperationFactor);
		Efloys.envpars[9].value = readFloat(EnvChoices[5], floys[num].MaxEnergyDose);
		Efloys.envpars[10].value = readFloat(EnvChoices[6], floys[num].MaxSafetyDose);
		Efloys.envpars[11].value = readFloat(EnvChoices[7], floys[num].MaxCooperationDose);
		Efloys.envpars[8].value = readFloat(EnvChoices[8], floys[num].SurviversFactor);
		Efloys.envpars[13].value = readFloat(EnvChoices[9], floys[num].FreeWillFactor);
		Efloys.envpars[14].value = readFloat(EnvChoices[10], floys[num].LifeSpan);

		Efloys.NF = readInt(EnvChoices[0], floys[num].PopulationSize);
		Efloys.KICK = readFloat(EnvChoices[9], floys[num].FreeWillFactor);
		Efloys.SLEEP = (long) readInt(EnvChoices[1], floys[num].sleep);

	}


	private void UpdateFloy(int num) {

		int id = Efloys.Efloys[num].id;
		int e = Efloys.Efloys[num].energy;
		int s = Efloys.Efloys[num].safety;
		int c = Efloys.Efloys[num].cooperation;
		String newpars = Efloys.EncodeChrom(Efloys.params);
		String newfixs = Efloys.EncodeChrom(Efloys.fixpars);
		String newenvs = Efloys.EncodeChrom(Efloys.envpars);
		floys[num] = new Efloy(Efloys.params, newpars, Efloys.fixpars, newfixs, Efloys.envpars, newenvs);
		Efloys.Efloys[num].id = id;
		Efloys.Efloys[num].energy = e;
		Efloys.Efloys[num].safety = s;
		Efloys.Efloys[num].cooperation = c;
		Efloys.Efloys[num].GetFitness();

	}


	void UpdateOne(int num) {

		int i;

		/*
		for (i=0;i<floys.length;i++) {
			UpdateEnviron(i);
			UpdateFloy(i);
		}
		*/

		UpdateEnviron(0);
		UpdateFloy(0);

		UpdateVariable(num);
		UpdateSecondary(num);
		UpdateFixed(num);
		UpdateFloy(num);


	}


	void UpdateAll() {

		int i;

		for (i=0;i<floys.length;i++) {
			UpdateVariable(i);
			UpdateSecondary(i);
			UpdateFixed(i);
			UpdateEnviron(i);
			UpdateFloy(i);
		}

	}


	void UpdateTitle(String title, boolean WithNumber) {

		TitleLabel.setText(title);
		if (WithNumber) {
			Floynum.show();
			FloynumLabel.show();
		}
		else {
			Floynum.hide();
			FloynumLabel.hide();
		}

		TitleLabel.resize(TitleLabel.preferredSize());
		FloynumLabel.resize(FloynumLabel.preferredSize());

	}


    public boolean action(Event e, Object arg) {


		int i, j, num;

        if (e.target == Environ) {
			UpdateTitle("Environmental Properties", false);
			cardLayout.show(InputPanel,"Environ");
		}
        if (e.target == Fixed) {
			UpdateTitle("Fixed Traits", true);
			cardLayout.show(InputPanel,"Fixed");
		}
        if (e.target == Variable) {
			UpdateTitle("Primary Traits", true);
			cardLayout.show(InputPanel,"Variable");
		}
        if (e.target == Secondary) {
			UpdateTitle("Secondary Traits", true);
			cardLayout.show(InputPanel,"Secondary");
		}
        if (e.target == Test) {
			UpdateTitle("Test Screen", false);
			cardLayout.show(InputPanel,"Test");
		}
        if (e.target == Update) {
			num = GetSelectedFloy(Floynum);
			UpdateOne(num);
			UpdateControls(Floynum);
			if (Efloys.envpars[12].value != CurPop) {
				Efloys.ResetPopulation = true;
				Efloys.First = true;
			}
		}
        if (e.target == Updateall) {
			UpdateAll();
			UpdateControls(Floynum);
			Efloys.ResetPopulation = true;
			Efloys.First = true;
		}
        if (e.target == Floynum) {
			UpdateControls(Floynum);
		}
        if (e.target == Quit) {
            this.hide();
            this.dispose();
            return true;
        }
		
		TitleLabel.resize(TitleLabel.preferredSize());
        return true;

	}


	public boolean handleEvent(Event ev) {

	    if (ev.id == Event.WINDOW_DESTROY) {
            this.hide();
            this.dispose();
            return true;
		}
		else
			return super.handleEvent(ev);

	} 

}
    