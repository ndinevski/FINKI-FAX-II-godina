using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BallsHitting
{
    public partial class Form1 : Form
    {
        Scene scene;
        public bool SceneMoving = false;
        int ticks = 0;
        public Form1()
        {
            InitializeComponent();
            scene = new Scene(Width, Height);
            timer1.Start();
            DoubleBuffered = true;
            MaximizeBox= true;
            MinimizeBox= true;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                scene.AddBall(new Ball(e.Location, Color.Red, 0));
            }
            Invalidate();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

    
        private void timer1_Tick(object sender, EventArgs e)
        {
            if (SceneMoving)
            {
                scene.Move();
                scene.CheckHit();
                ticks++;
            }
            UpdateStatus();
            Invalidate();
        }

        private void sTARTToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(sTARTToolStripMenuItem.Text == "START")
            {
                sTARTToolStripMenuItem.Text = "PAUSE";
                SceneMoving = true;
            }
            else
            {
                sTARTToolStripMenuItem.Text = "START";
                SceneMoving = false;
            }
            Invalidate();
        }

        public void UpdateStatus()
        {
            toolStripStatusLabel1.Text = scene.balls.Count.ToString();
            toolStripStatusLabel2.Text = $"{ticks / 600:D2}:{(ticks % 600) / 10:D2}";
        }

        private void toolStripStatusLabel1_Click(object sender, EventArgs e)
        {

        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog sfd = new SaveFileDialog();
            sfd.Title = "Save";
            if (sfd.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(sfd.FileName, FileMode.OpenOrCreate);
                IFormatter f = new BinaryFormatter();
                f.Serialize(fs, scene);
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            ofd.Title = "Open";
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(ofd.FileName, FileMode.Open);
                IFormatter f = new BinaryFormatter();
                scene = (Scene) f.Deserialize(fs);
            }
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene = new Scene(Width, Height);
            timer1.Start();
        }
    }
}
