//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//

import java.awt.*;

final class EfloyPredefined extends Frame {

	Button Button1;
	Button Button2;
	Button Button3;
	Button Button4;
	Button ButtonDefault;
	Button ButtonCancel;


	Label Label1; 
	Label Label2; 
	Label Label3; 
	Label Label4; 
	Label LabelDefault;
	Label LabelCancel;
	
	Label Help;

	Label Labels[];
	Button[] Buttons;
	Label[] DummiesA;
	Label[] DummiesB;
	Label[] DummiesC;

	Panel MainPanel;
	Label TitleLabel;

	Font ButtonFont;

    public EfloyPredefined () {
        super("Predefined Populations and Environments");
		int i,j;
        
		ButtonFont = new Font("TimesRoman",Font.PLAIN,12);
		setBackground(Color.white);
		setForeground(Color.black);
		InitControls();

		Help = new Label("These predefined configurations are arbitrary samples. Each can be defined in the Edit Properties screen",Label.CENTER);
		Help.setForeground(Color.blue);
        Panel StatusPanel = new Panel();
        StatusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        StatusPanel.add(Help);
		Font StatusFont = new Font("TimesRoman",Font.BOLD,12);
		Help.setFont(StatusFont);
        this.add("South", StatusPanel);

        Panel TitlePanel = new Panel();
        TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		TitleLabel = new Label("  Predefined Populations and Environments  ");
		TitlePanel.add(TitleLabel);
		this.add("North",TitlePanel);
		Font TitleFont = new Font("TimesRoman",Font.BOLD,14);
		TitlePanel.setFont(TitleFont);
		TitlePanel.setForeground(Color.blue);

        MainPanel = new Panel();
		MainPanel.setLayout(new GridLayout(10,1));
		InitMainPanel(MainPanel, Labels, Buttons, DummiesA, DummiesB, DummiesC);

		this.add("Center",MainPanel);

		this.pack();
		this.hide();

	}


	private void InitControls() {

		int i, j;

		Font LabelFont = new Font("TimesRoman",Font.BOLD,12);
		Font ButtonFont = new Font("TimesRoman",Font.BOLD,12);

		Button1 = new Button("Nervous Population");
		Button2 = new Button("Phlegmatic Population");
		Button3 = new Button("Cautious Evolution");
		Button4 = new Button("Risk Taking Evolution");
		ButtonDefault = new Button("Select Default");
		ButtonCancel = new Button("Cancel");

		Button1.setFont(ButtonFont);
		Button2.setFont(ButtonFont);
		Button3.setFont(ButtonFont);
		Button4.setFont(ButtonFont);
		ButtonDefault.setFont(ButtonFont);
		ButtonCancel.setFont(ButtonFont);

		Labels = new Label[11];
		Buttons = new Button[11];
		DummiesA = new Label[11];
		DummiesB = new Label[11];
		DummiesC = new Label[11];

		for (i=0;i<11;i++) {
			Labels[i] = new Label("");
			Buttons[i] = new Button("");
			DummiesA[i] = new Label("");
			DummiesB[i] = new Label("");
			DummiesC[i] = new Label("");
			Labels[i].setFont(LabelFont);
			Buttons[i].setFont(ButtonFont);
		}

		Labels[0] = new Label("Configuration #1: Energetic, fast nervous population");
		Labels[1] = new Label("Configuration #2: Slow, lazy, phlegmatic population");
		Labels[2] = new Label("Configuration #3: Evolution that favors safety over energy");
		Labels[3] = new Label("Configuration #4: Evolution that favors energy over safety");
		Labels[4] = new Label("Reset to Default configuration");
		Labels[5] = new Label("Keep existing environment and population");

		for (i=0;i<6;i++) {
			Labels[i].setFont(LabelFont);
			Buttons[i].setFont(ButtonFont);
		}

		Buttons[0] = Button1;
		Buttons[1] = Button2;
		Buttons[2] = Button3;
		Buttons[3] = Button4;
		Buttons[4] = ButtonDefault;
		Buttons[5] = ButtonCancel;

	}
	

	
	private void InitMainPanel(Panel panel, Label[] labels, Button[] buttons, Label[] dummiesa, Label[] dummiesb, Label[] dummiesc) {

		int i;

		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		for (i=0;i<6;i++) {
 			constraints.gridwidth = 1;
			layout.setConstraints(dummiesa[i], constraints);
			panel.add(dummiesa[i]);

			constraints.gridwidth = 3;
			layout.setConstraints(Labels[i], constraints);
			panel.add(labels[i]);

			constraints.gridwidth = 1;
			layout.setConstraints(buttons[i], constraints);
			panel.add(Buttons[i]);


			constraints.gridwidth = GridBagConstraints.REMAINDER;
			layout.setConstraints(dummiesb[i], constraints);
			panel.add(dummiesb[i]);

		}

		panel.resize(panel.preferredSize());

	}
	


	void DefaultParams() {

		Efloys.params[0].value = 5f;		//MaxSpeed
		Efloys.params[1].value = 0.8f;		//BounceSpeed
		Efloys.params[2].value = 0.3f;		//ApproachAcceleration
		Efloys.params[4].value = 0.1f;		//CenterAcceleration
		Efloys.params[5].value = 1f;			//DistBrotherFactor
		Efloys.params[6].value = 20f;		//DistStrangerFactor
		Efloys.params[7].value = 0f;			//DistLocalFactor
		Efloys.params[8].value = 200f;		//CollisionDistance
		Efloys.params[9].value = -1f;		//CollisionBrotherFactor
		Efloys.params[10].value = 30f;		//CollisionStrangerFactor
		Efloys.params[11].value = -40f;		//CollisionLocalFactor

		Efloys.fixpars[5].value = 2f;		//NumberOfNeighbors
		Efloys.fixpars[6].value = 0.1f;		//MutationFactor
		Efloys.fixpars[7].value = 1f;		//CrossoverFactor
		Efloys.fixpars[8].value = 10f;		//energy
		Efloys.fixpars[9].value = 10f;		//safety
		Efloys.fixpars[10].value = 10f;		//cooperation

		Efloys.envpars[5].value = 1f;		//EnergyFactor
		Efloys.envpars[6].value = 1f;		//SafetyFactor
		Efloys.envpars[7].value = 0f;		//CooperationFactor
		Efloys.envpars[8].value = 0f;		//SurviversFactor
		Efloys.envpars[12].value = 15f;		//PopulationSize
		Efloys.envpars[13].value = 0.05f;	//FreeWillFactor"
		Efloys.envpars[14].value = 50f;		//LifeSpan

		Efloys.NF = (int) Efloys.envpars[12].value;
		Efloys.SLEEP = (long) Efloys.envpars[3].value;
		Efloys.KICK =  Efloys.envpars[13].value;


	}


	void UpdateParams(int num) {

		DefaultParams();

		if (num == 1) {
			Efloys.params[0].value = 8f;		//MaxSpeed
			Efloys.params[1].value = 2f;		//BounceSpeed
			Efloys.params[2].value = 0.8f;		//ApproachAcceleration
			Efloys.params[4].value = 0.1f;		//CenterAcceleration
			Efloys.params[5].value = 3f;		//DistBrotherFactor
			Efloys.params[6].value = 40f;		//DistStrangerFactor
			Efloys.params[7].value = 0f;		//DistLocalFactor
			Efloys.params[8].value = 200f;		//CollisionDistance
			Efloys.params[9].value = -1f;		//CollisionBrotherFactor
			Efloys.params[10].value = 50f;		//CollisionStrangerFactor
			Efloys.params[11].value = -50f;		//CollisionLocalFactor
			Efloys.InEvolution = false;
			Efloys.NewGeneration = false;
			Efloys.Evolution.setLabel("Start Evolution");
		}
		else
		if (num == 2) {
			Efloys.params[0].value = 1f;		//MaxSpeed
			Efloys.params[1].value = 0.1f;		//BounceSpeed
			Efloys.params[2].value = 0.05f;		//ApproachAcceleration
			Efloys.params[4].value = 0f;		//CenterAcceleration
			Efloys.params[5].value = 1f;		//DistBrotherFactor
			Efloys.params[6].value = 10f;		//DistStrangerFactor
			Efloys.params[7].value = 0f;		//DistLocalFactor
			Efloys.params[8].value = 200f;		//CollisionDistance
			Efloys.params[9].value = -1f;		//CollisionBrotherFactor
			Efloys.params[10].value = 10f;		//CollisionStrangerFactor
			Efloys.params[11].value = -20f;		//CollisionLocalFactor
			Efloys.InEvolution = false;
			Efloys.NewGeneration = false;
			Efloys.Evolution.setLabel("Start Evolution");
		}
		else
		if (num == 3) {
 			Efloys.envpars[5].value = 1f;		//EnergyFactor
			Efloys.envpars[6].value = 3f;		//SafetyFactor
			Efloys.envpars[7].value = 0f;		//CooperationFactor
			Efloys.envpars[8].value = 0f;		//SurviversFactor
			Efloys.envpars[12].value = 25f;		//PopulationSize
			Efloys.envpars[13].value = 0.05f;	//FreeWillFactor"
			Efloys.envpars[14].value = 25f;		//LifeSpan
			Efloys.InEvolution = true;
			Efloys.NewGeneration = true;
			Efloys.Evolution.setLabel("Stop Evolution");
		}
		else
		if (num == 4) {
 			Efloys.envpars[5].value = 3f;		//EnergyFactor
			Efloys.envpars[6].value = 1f;		//SafetyFactor
			Efloys.envpars[7].value = 0f;		//CooperationFactor
			Efloys.envpars[8].value = 0f;		//SurviversFactor
			Efloys.envpars[12].value = 25f;		//PopulationSize
			Efloys.envpars[13].value = 0.05f;	//FreeWillFactor"
			Efloys.envpars[14].value = 25f;		//LifeSpan
			Efloys.InEvolution = true;
			Efloys.NewGeneration = true;
			Efloys.Evolution.setLabel("Stop Evolution");
		}

		Efloys.NF = (int) Efloys.envpars[12].value;
		Efloys.SLEEP = (long) Efloys.envpars[3].value;
		Efloys.KICK =  Efloys.envpars[13].value;

	}


	public boolean mouseEnter(Event evt, int x, int y) {

	
		if (evt.target == Button1) {
			Help.setText("Population #1 Main Properties:  Max Speed = 8, Bounce = 2, Acceleration = 0.8");
		}
		if (evt.target == Button2) {
			Help.setText("Population #2 Main Properties:  Max Speed = 1, Bounce = 0.1, Acceleration = 0.1");
		}
		if (evt.target == Button3) {
			Help.setText("Evolution #3 Main Properties:  Energy Weight Factor = 1, Safety Weight Factor = 3, Life Span = 25");
		}
		if (evt.target == Button4) {
			Help.setText("Evolution #4 Main Properties:  Energy Weight Factor = 3, Safety Weight Factor = 1, Life Span = 25");
		}
		if (evt.target == ButtonDefault) {
			Help.setText("All properties reset to defaults (Initial properties)");
		}
		if (evt.target == ButtonCancel) {
			Help.setText("Quit this screen without changing anything");
		}
		
		return true;
	}



	public boolean mouseExit(Event evt, int x, int y) {

		Help.setText("");
		return true;

	}



    public boolean action(Event e, Object arg) {


		int i, j, num;
		boolean ok = true;

        if (e.target == Button1) {
			UpdateParams(1);
		}
        if (e.target == Button2) {
			UpdateParams(2);
		}
        if (e.target == Button3) {
			UpdateParams(3);
		}
        if (e.target == Button4) {
			UpdateParams(4);
			Efloys.appcontext.showStatus("Button #4");
		}
        if (e.target == ButtonDefault) {
			DefaultParams();
			Efloys.appcontext.showStatus("Button #5");
		}
        if (e.target == ButtonCancel) {
			ok = false;
		}
		
		//TitleLabel.resize(TitleLabel.preferredSize());
		if (ok) {
			Efloys.ResetPopulation = true;
			Efloys.First = true;
		}

        this.setVisible(false);
        this.dispose();
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
    