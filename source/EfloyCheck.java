//
//	This code was written by Ariel Dolan
//	Site	http://www.aridolan.com
//	Email	aridolan@netvision.net.il
//
//	You are welcome to do whatever you wish with this code, as long as you
//	add appropriate credits.
//

//package Efloys;

import java.awt.*;

final class EfloyCheck extends Frame {
    Button cancel;
    Button ok;
    Button reset;
	Button defaults;
	Button leader;
    Efloy floys[];
    TextField nf;
    TextField acc;
    TextField acctomid;
	TextField revdist;
    TextField maxspeed;
    TextField bouncespeed;
    TextField sleep;
    TextField v0;
    TextField kick;
	TextField numnb;
	TextField color;
	TextField floynum;
	Label label;
	TextField chromo;
	String newpars;
	String newfixs;
	String newenvs;

    
    public EfloyCheck (Efloy f[]) {
        super("Floy Check Panel");
        floys = f;
		float fi = 0f;
        
        cancel = new Button("Cancel");
        ok = new Button("OK");
        reset = new Button("Reset");
		defaults = new Button("Defaults");
		leader = new Button("Leader");
        Panel okPanel = new Panel();
        okPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        okPanel.add(cancel);
        okPanel.add(ok);
        okPanel.add(reset);
		okPanel.add(defaults);
		//okPanel.add(leader);
        this.add("South", okPanel);
        
		label = new Label("Properties (Global / Individual)");
        Panel titlePanel = new Panel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        titlePanel.add(label);
        this.add("North", titlePanel);
        //label.setText("f0 = "+f[0].acc+" floys0 = "+floys[0].acc);

        nf = new TextField();
        floynum = new TextField();
		color = new TextField();
        acc = new TextField();
        acctomid = new TextField();
        revdist = new TextField();
        maxspeed = new TextField();
        bouncespeed = new TextField();
        sleep = new TextField();
		kick = new TextField();
		numnb = new TextField();
		chromo = new TextField(20);
		
		reset(1);

        Panel controlPanel = new Panel();
        controlPanel.setLayout(new GridLayout(12, 2, 0, 0)); // 12 rows, 2 columns, 0 horizontal, 5 verticle
        controlPanel.add(new Label("Number of Floys:"));
		controlPanel.add(nf);
        controlPanel.add(new Label("Delay:"));         
		controlPanel.add(sleep);
        controlPanel.add(new Label("Free Will Factor:"));      
		controlPanel.add(kick);
		controlPanel.add(new Label("Number of Neighbors:"));      
		controlPanel.add(numnb);
		

		controlPanel.add(new Label("-----------",Label.CENTER));
		controlPanel.add(new Label("-----------",Label.CENTER));

        controlPanel.add(new Label("Applies to Floy No.:"));
		controlPanel.add(floynum);
        controlPanel.add(new Label("Floy Color:"));
		controlPanel.add(color);
        controlPanel.add(new Label("Acceleration:"));      
		controlPanel.add(acc);
        controlPanel.add(new Label("Adhesion:"));     
		controlPanel.add(acctomid);
        controlPanel.add(new Label("Collision Distance:"));    
		controlPanel.add(revdist);
        controlPanel.add(new Label("Max. Speed:"));	
		controlPanel.add(maxspeed);
        //controlPanel.add(new Label("Bounce Speed:"));       
		//controlPanel.add(bouncespeed);
        controlPanel.add(new Label("Chromosome:"));       
		controlPanel.add(chromo);
		
		

        //controlPanel.add(new Label("Test:"));        
		//controlPanel.add(new Label(Long.toString(Efloys.NF)));
        Panel alignPanel = new Panel();
        alignPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        alignPanel.add("Center", controlPanel);
        this.add("Center", alignPanel);
        
        this.pack();
        this.show();
        }
    

	private void reset(int num) {

		if (num == 1) {
	        nf.setText(""+Efloys.NF);
			floynum.setText("1");
		    acc.setText(""+Efloys.ACC);
			acctomid.setText(""+Efloys.ACCTOMID);
	        revdist.setText(""+Efloys.REVDIST);
		    maxspeed.setText(""+Efloys.MAXSPEED);
			bouncespeed.setText(""+Efloys.BOUNCESPEED);
	        sleep.setText(""+Efloys.SLEEP);
		    color.setText("Green");
			kick.setText(""+Efloys.KICK);
			numnb.setText(""+Efloys.NUMNB);
			chromo.setText("");
		}
		else {
	        nf.setText("10");
			floynum.setText("1");
		    acc.setText("0.3");
			acctomid.setText("0.1");
	        revdist.setText("200");
		    maxspeed.setText("5");
			bouncespeed.setText("0.8");
	        sleep.setText("10");
		    color.setText("Green");
			kick.setText("0.05");
			numnb.setText("2");
			chromo.setText("");
		}


	}




    public boolean action(Event e, Object arg) {

		String fnum;
		int num;

        if (e.target == reset) {
			reset(1);
			}
        if (e.target == defaults) {
            Efloys.reset();
			reset(1);
			}

        if (e.target == ok) {
			fnum = readString(floynum, "All");
			if (fnum.equals("All")) {
				Efloys.ResetPopulation = true;
	            Efloys.NF = readInt(nf, Efloys.NF);
				Efloys.ACC = readFloat(acc, Efloys.ACC);
				Efloys.ACCTOMID = readFloat(acctomid, Efloys.ACCTOMID);
				Efloys.REVDIST = readInt(revdist, Efloys.REVDIST);
				Efloys.MAXSPEED = readFloat(maxspeed, Efloys.MAXSPEED);
				Efloys.BOUNCESPEED = readFloat(bouncespeed, Efloys.BOUNCESPEED);
				Efloys.SLEEP = readLong(sleep, Efloys.SLEEP);
				Efloys.COLOR = readString(color, Efloys.COLOR);
				Efloys.KICK = readFloat(kick, Efloys.KICK);
				Efloys.NUMNB = readInt(numnb, Efloys.NUMNB);

			}
			else {
				Efloys.ResetPopulation = false;
				if (fnum.equals("Stranger"))
					num = 0;
				else
					num = Integer.parseInt(fnum);
				/*
				floys[num].ApproachAcceleration = readFloat(acc, floys[num].ApproachAcceleration);
				floys[num].CenterAcceleration = readFloat(acctomid, floys[num].CenterAcceleration);
				floys[num].CollisionDistance = readInt(revdist, floys[num].CollisionDistance);
				floys[num].MaxSpeed = readFloat(maxspeed, floys[num].MaxSpeed);
				floys[num].BounceSpeed = readFloat(bouncespeed, floys[num].BounceSpeed);
				floys[num].color = readColor(color, floys[num].GetColorName());
				//floys[num].color = GetColorByString(color.getSelectedItem());
				//floys[num].kick = readFloat(kick, floys[num].kick);
				floys[num].NumberOfNeighbors = readInt(numnb, floys[num].NumberOfNeighbors);
				if (floys[num].color == Color.red) 
					floys[num].type = 1;
				else
					floys[num].type = 0;
				floys[num].chrom = chromo.getText();
				*/
				//UpdateChrom(num);
				//floys[num] = new Efloy(Efloys.params, newpars, Efloys.fixpars, newfixs, Efloys.envpars, newenvs);
			}

            this.hide();
            this.dispose();
			Efloys.First = true;
            return true;
            }

        if (e.target == cancel) {
			Efloys.First = true;
            this.hide();
            this.dispose();
            return true;
            }
		

		if (e.target == floynum) {

			fnum = readString(floynum, "All");
			if (!fnum.equals("All")) {
				if (fnum.equals("Stranger"))
					num = 0;
				else
					num = Integer.parseInt(fnum);

				acc.setText(""+floys[num].ApproachAcceleration);
				acctomid.setText(""+floys[num].CenterAcceleration);
				revdist.setText(""+floys[num].CollisionDistance);
				maxspeed.setText(""+floys[num].MaxSpeed);
				bouncespeed.setText(""+floys[num].BounceSpeed);
				color.setText(""+floys[num].GetColorName());
				//kick.setText(""+floys[num].kick);
				numnb.setText(""+floys[num].NumberOfNeighbors);
				chromo.setText(floys[num].chrom);
			}

            return true;

		}
		

        else
            return false;
        }
    

    private String readString(TextField c, String d) {
        String n;
        
        try {
            n = c.getText();
            }
        catch (Exception e) {
            n = d;
            }
        
        return n;
        }


    private int readInt(TextField c, int d) {
        int n;
        
        try {
            n = Integer.parseInt(c.getText());
            }
        catch (NumberFormatException e) {
            n = d;
            }
        
        return n;
        }

    private long readLong(TextField c, long d) {
        long n;
        
        try {
			n = Long.parseLong(c.getText().trim());
            }
        catch (NumberFormatException e) {
            n = d;
            }
        
        return n;
        }
		
    private float readFloat(TextField c, float d) {
        float n;
        
        try {
            n = Float.valueOf(c.getText()).floatValue();
            }
        catch (NumberFormatException e) {
            n = d;
            }
        
        return n;
        }



    private Color ReadColor(TextField c, String d) {
        String n;
		Color col;
        
        try {
            n = c.getText();
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


}
