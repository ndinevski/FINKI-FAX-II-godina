using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BallsPulsing
{
    public partial class Form1 : Form
    {
        Scene scene;
        Random rand = new Random();
        int TimeLeft = 120;
        public Form1()
        {
            InitializeComponent();
            scene = new Scene();
            DoubleBuffered = true;
            timer1.Start();
            lbTimer.Text = "02:00";
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            if (!scene.isHit(e.Location))
            {
                scene.addShape(new Circle(e.Location, rand.Next(15, 80)));
            }
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            TimeLeft--;
            scene.Pulse();
            lbTimer.Text = $"{TimeLeft/60:D2}:{TimeLeft%60:D2}";
            Invalidate();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.KeyCode == Keys.Right)
            {
                scene.MoveRight();
            }
            Invalidate();
        }
    }
}
