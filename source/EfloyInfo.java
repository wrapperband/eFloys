//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//

import java.awt.*;
import java.util.*;
//import VE.JavaGRID.*;


final class EfloyInfo extends Frame {

    Button primary;
    Button secondary;
    Button environ;
	Button chromo;
	Button fitness;
	Button history;
	//Button log;
	Button quitit;
	Button test;
	
	Panel okPanel;
	Panel TitlePanel;
	Panel TablePanel;
	Panel PrimaryTable;
	Panel SecondaryTable;
	Panel EnvironTable;
	Panel ChromoTable;
	Panel FitnessTable;
	Panel HistoryTable;
	Panel TestTable;

	Label TitleLabel;
	CardLayout cardLayout;
	EfloyScrollPanel sp;

    Efloy floys[];
    
    public EfloyInfo (Efloy f[]) {
        super("Floy Population Information");
        floys = f;
		float fi = 0f;
		int i,j;
        
		setBackground(Color.white);
		setForeground(Color.black);

        primary = new Button("Primary");
        secondary = new Button("Secondary");
        environ = new Button("Environmental");
		chromo = new Button("Chromosomes");
		fitness = new Button("Fitness");
		history = new Button("History");
		//log = new Button("Log File");
		quitit = new Button("Quit");
		test = new Button("Test");

        okPanel = new Panel();
        okPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        okPanel.add(primary);
        okPanel.add(secondary);
        okPanel.add(environ);
		//okPanel.add(chromo);
		okPanel.add(fitness);
		okPanel.add(history);
		//okPanel.add(log);
		okPanel.add(test);
		okPanel.add(quitit);
        this.add("South", okPanel);
		Font ButtonFont = new Font("TimesRoman",Font.PLAIN,12);
		okPanel.setFont(ButtonFont);

        TitlePanel = new Panel();
        TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		TitleLabel = new Label("Primary Traits");
		TitlePanel.add(TitleLabel);
		this.add("North",TitlePanel);
		Font TitleFont = new Font("TimesRoman",Font.BOLD,12);
		TitlePanel.setFont(TitleFont);
		TitlePanel.setForeground(Color.blue);

        TablePanel = new Panel();
		cardLayout = new CardLayout(0,0);
		TablePanel.setLayout(cardLayout);

		PrimaryTable = new Panel();
		PrimaryTable.setLayout(new GridLayout(0,10));
		FillPrimary(PrimaryTable);
		//TablePanel.add("Primary",PrimaryTable);
		sp = new EfloyScrollPanel(PrimaryTable, "V");
		TablePanel.add("Primary",sp);

		SecondaryTable = new Panel();
		SecondaryTable.setLayout(new GridLayout(0,7));
		FillSecondary(SecondaryTable);
		//TablePanel.add("Secondary",SecondaryTable);
		sp = new EfloyScrollPanel(SecondaryTable, "V");
		TablePanel.add("Secondary",sp);


		EnvironTable = new Panel();
		EnvironTable.setLayout(new GridLayout(0,2));
		FillEnviron(EnvironTable);
		//TablePanel.add("Environ",EnvironTable);
		sp = new EfloyScrollPanel(EnvironTable, "V");
		TablePanel.add("Environ",sp);


		/*
		ChromoTable = new Panel();
        ChromoTable.setLayout(new GridLayout(floys.length+2,5));
		FillChromo(ChromoTable);
		TablePanel.add("Chromo",ChromoTable);
		*/

		FitnessTable = new Panel();
		FitnessTable.setLayout(new GridLayout(0,5));
		FillFitness(FitnessTable);
		//TablePanel.add("Fitness",FitnessTable);
		sp = new EfloyScrollPanel(FitnessTable, "V");
		TablePanel.add("Fitness",sp);


		HistoryTable = new Panel();
		HistoryTable.setLayout(new GridLayout(0,10));
		FillHistory(HistoryTable);
		//TablePanel.add("History",HistoryTable);
		sp = new EfloyScrollPanel(HistoryTable, "V");
		TablePanel.add("History",sp);


		this.add("Center",TablePanel);

		this.pack();
		//this.show();

	}


	private void FillPrimary(Panel panel) {

		int i;
		SortFloys();

		panel.add(new Label("#"));
		panel.add(new Label("ID"));
		//panel.add(new Label("Chromosome"));
		panel.add(new Label("Speed"));
		panel.add(new Label("Bounce"));
		panel.add(new Label("Acc."));
		panel.add(new Label("Center"));
		panel.add(new Label("Collision"));
		panel.add(new Label("Color"));
		panel.add(new Label("Fitness"));
		panel.add(new Label("Energy"));

		for (i=0;i < floys.length;i++) {
			panel.add(new Label(""+i));
			panel.add(new Label(""+floys[i].id));
			//panel.add(new Label(""+floys[i].chrom));
			panel.add(new Label(""+floys[i].MaxSpeed));
			panel.add(new Label(""+floys[i].BounceSpeed));
			panel.add(new Label(""+floys[i].ApproachAcceleration));
			panel.add(new Label(""+floys[i].CenterAcceleration));
			panel.add(new Label(""+floys[i].CollisionDistance));
			panel.add(new Label(""+floys[i].GetColorName()));
			panel.add(new Label(""+floys[i].fitness));
			panel.add(new Label(""+floys[i].energy));
		}

	}


	private void FillSecondary(Panel panel) {

		int i;

		panel.add(new Label("ID"));
		panel.add(new Label("Distance"));
		panel.add(new Label("Distance"));
		panel.add(new Label("Distance"));
		panel.add(new Label("Collision"));
		panel.add(new Label("Collision"));
		panel.add(new Label("Collision"));

		panel.add(new Label(" "));
		panel.add(new Label("Brother"));
		panel.add(new Label("Stranger"));
		panel.add(new Label("Local"));
		panel.add(new Label("Brother"));
		panel.add(new Label("Stranger"));
		panel.add(new Label("Local"));

		for (i=0;i < floys.length;i++) {
			panel.add(new Label(""+floys[i].id));
			panel.add(new Label(""+floys[i].DistBrotherFactor));
			panel.add(new Label(""+floys[i].DistStrangerFactor));
			panel.add(new Label(""+floys[i].DistLocalFactor));
			panel.add(new Label(""+floys[i].CollisionBrotherFactor));
			panel.add(new Label(""+floys[i].CollisionStrangerFactor));
			panel.add(new Label(""+floys[i].CollisionLocalFactor));
		}

	}



	private void FillEnviron(Panel panel) {


		panel.add(new Label("Population Size"));
		panel.add(new Label(""+floys[0].PopulationSize));

		panel.add(new Label("Number of Neighbors"));
		panel.add(new Label(""+floys[0].NumberOfNeighbors));

		panel.add(new Label("Crossover Factor"));
		panel.add(new Label(""+floys[0].CrossoverFactor));

		panel.add(new Label("Mutation Factor"));
		panel.add(new Label(""+floys[0].MutationFactor));

		panel.add(new Label("Energy Weight Factor"));
		panel.add(new Label(""+floys[0].EnergyFactor));

		panel.add(new Label("Safety Weight Factor"));
		panel.add(new Label(""+floys[0].SafetyFactor));

		panel.add(new Label("Cooperation Weight Factor"));
		panel.add(new Label(""+floys[0].CooperationFactor));

		panel.add(new Label("Life Span"));
		panel.add(new Label(""+floys[0].LifeSpan));

		panel.add(new Label("Free Will Factor"));
		panel.add(new Label(""+floys[0].FreeWillFactor));

		panel.add(new Label("Max. Energy Dose"));
		panel.add(new Label(""+floys[0].MaxEnergyDose));

		panel.add(new Label("Max. Safety Dose"));
		panel.add(new Label(""+floys[0].MaxSafetyDose));

		panel.add(new Label("Max. Cooperation Dose"));
		panel.add(new Label(""+floys[0].MaxCooperationDose));

		panel.add(new Label("Survivers Percent"));
		panel.add(new Label(""+floys[0].SurviversFactor));

		panel.add(new Label("Movement Slowdown Property"));
		panel.add(new Label(""+floys[0].sleep));


	}


	private void FillChromo(Panel panel) {

		int i;

		panel.add(new Label("ID"));
		panel.add(new Label("Variable"));
		panel.add(new Label("Fixed"));
		panel.add(new Label("Environmental"));
		panel.add(new Label("Color"));

		for (i=0;i < floys.length;i++) {
			panel.add(new Label(""+floys[i].id));
			panel.add(new Label(""+floys[i].chrom));
			panel.add(new Label(""+floys[i].fixed));
			panel.add(new Label(""+floys[i].environ));
			panel.add(new Label(""+floys[i].GetColorName()));
		}

	}


	private void FillFitness(Panel panel) {

		int i;

		panel.add(new Label("ID"));
		panel.add(new Label("Energy"));
		panel.add(new Label("Safety"));
		panel.add(new Label("Cooperation"));
		panel.add(new Label("Fitness"));

		for (i=0;i < floys.length;i++) {
			panel.add(new Label(""+floys[i].id));
			panel.add(new Label(""+floys[i].energy));
			panel.add(new Label(""+floys[i].safety));
			panel.add(new Label(""+floys[i].cooperation));
			panel.add(new Label(""+floys[i].fitness));
		}

	}


	private void FillHistory(Panel panel) {

		int i, j, k, n, m;
		EfloyGeneration g = new EfloyGeneration();

		/*
		panel.add(new Label("#"));
		panel.add(new Label("Steps"));
		panel.add(new Label("Average"));
		panel.add(new Label("Average"));
		panel.add(new Label("Distance"));
		panel.add(new Label("Distance"));
		panel.add(new Label("Distance"));
		panel.add(new Label("Collision"));
		panel.add(new Label("Collision"));
		panel.add(new Label("Collision"));
		*/

		panel.add(new Label("#"));
		panel.add(new Label("Steps "));
		panel.add(new Label("Speed"));
		panel.add(new Label("Accel."));
		panel.add(new Label("DLL"));
		panel.add(new Label("DLS"));
		panel.add(new Label("DSL"));
		panel.add(new Label("CLL"));
		panel.add(new Label("CLS"));
		panel.add(new Label("CSL"));

		//Enumeration e = Efloys.HistoryData.elements();
		n = Efloys.HistoryData.size();
		m = (int) (n/24+1);
		Enumeration  e  =  Efloys.HistoryData.elements();

		k=0;
		//for  (Enumeration  e  =  Efloys.HistoryData.elements();e.hasMoreElements();)  {	
		//for (i=0;i<n;i += m) {
		for (i=0;i<n;i++) {
			//k++;
			
			try {
				//for (j=0;j<m;j++)
				g = ((EfloyGeneration) e.nextElement());
				k++;
				panel.add(new Label(""+g.num));
				panel.add(new Label(""+g.steps));
				panel.add(new Label(""+double2String(g.speedavg,2)));
				panel.add(new Label(""+double2String(g.accavg, 2)));
				panel.add(new Label(""+double2String(g.dllavg, 2)));
				panel.add(new Label(""+double2String(g.dlsavg, 2)));
				panel.add(new Label(""+double2String(g.dslavg, 2)));
				panel.add(new Label(""+double2String(g.cllavg, 2)));
				panel.add(new Label(""+double2String(g.clsavg, 2)));
				panel.add(new Label(""+double2String(g.cslavg, 2)));
			}
			catch (Exception ex) {
			}
			
		}
		
		
		if (k<18) {
			for (i=k+1;i<17;i++)
				for (j=0;j<7;j++)
				   panel.add(new Label(" "));
		}
		

	}



public static String double2String( double value, int sigFigs ) {
	
    /*
	This way you're just shifting the places after the decimal up before the
	decimal, chopping the unwanted part off with round(), then shifting
	back down with division and the cast to double.  String will format the
	double for you after that.

	Christopher Robin Kessel        Portland, Oregon 
	chrisk@protocol.com             Protocol Systems 
    */

		double factor = Math.pow( 10.0, sigFigs );

		return new String( ( (double)Math.round( value * factor ) / factor ) + "" );
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


	public boolean mouseEnter(Event evt, int x, int y) {

		if (evt.target == primary) {
			Efloys.appcontext.showStatus("Display main traits");
		}
		if (evt.target == secondary) {
			Efloys.appcontext.showStatus("Display secondary traits");
		}
		if (evt.target == environ) {
			Efloys.appcontext.showStatus("Display environment characteristics");
		}
		if (evt.target == chromo) {
			Efloys.appcontext.showStatus("Display chromosome strings");
		}
		if (evt.target == fitness) {
			Efloys.appcontext.showStatus("Display fitness and fitness components");
		}
		if (evt.target == history) {
			Efloys.appcontext.showStatus("Display generation history");
		}
		if (evt.target == test) {
			Efloys.appcontext.showStatus("Display test page");
		}
		//if (evt.target == log) {
		//	Efloys.appcontext.showStatus("Display log file");
		//}
		if (evt.target == quitit) {
			Efloys.appcontext.showStatus("Exit info screen");
		}

		return true;

	}


	public boolean mouseExit(Event evt, int x, int y) {

		Efloys.appcontext.showStatus("");
		return true;

	}



    public boolean action(Event e, Object arg) {


		int i, j;

        if (e.target == primary) {
			TitleLabel.setText("Primary Traits");
			cardLayout.show(TablePanel,"Primary");
		}
        if (e.target == secondary) {
			TitleLabel.setText("Secondary Traits");
			cardLayout.show(TablePanel,"Secondary");
		}
        if (e.target == environ) {
			TitleLabel.setText("Environmental Characteristics");
			cardLayout.show(TablePanel,"Environ");
		}
        if (e.target == chromo) {
			TitleLabel.setText("The Chromosomes");
			cardLayout.show(TablePanel,"Chromo");
		}
        if (e.target == fitness) {
			TitleLabel.setText("Fitness Table");
			cardLayout.show(TablePanel,"Fitness");
		}
        if (e.target == history) {
			TitleLabel.setText("History");
			cardLayout.show(TablePanel,"History");
		}
        if (e.target == test) {
			TitleLabel.setText("Test");
			cardLayout.show(TablePanel,"Test");
		}

        //if (e.target == log) {
		//	TitleLabel.setText("");
		//	Efloys.deb.toFront();
		//}

        if (e.target == quitit) {
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
    