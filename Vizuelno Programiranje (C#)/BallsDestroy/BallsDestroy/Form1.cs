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

namespace BallsDestroy
{
    [Serializable]
    public partial class Form1 : Form
    {
        Scene scene;
        public int counter { get; set; }
        public int missedBalls { get; set; } = 0;
        public int hitBalls { get; set; } = 0;

        public Form1()
        {
            InitializeComponent();
            scene = new Scene(this.Width,this.Height);
            timer1.Start();
            DoubleBuffered= true;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void toolStripStatusLabel2_Click(object sender, EventArgs e)
        {

        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            scene.Move();
            counter++;
            if(counter % 7 == 0)
            {
                scene.AddBall(new Ball());
            }
            mBall();
            updateStatus();
            Invalidate();
        }

        private void updateStatus()
        {
            toolStripStatusLabel1.Text = $"Hit balls: {hitBalls}";
            toolStripStatusLabel2.Text = $"Miss balls: {missedBalls}";
        }

        private void mBall()
        {
            missedBalls = Scene.missedBalls;
            hitBalls = Scene.hitBalls;
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.hitBall(e.Location);
            Invalidate();
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene = new Scene(this.Width, this.Height);
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SaveFileDialog sfd = new SaveFileDialog();
            if (sfd.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(sfd.FileName, FileMode.OpenOrCreate);
                IFormatter formatter = new BinaryFormatter();
                formatter.Serialize(fs, scene);
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog ofd = new OpenFileDialog();
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                FileStream fs = new FileStream(ofd.FileName, FileMode.Open);
                IFormatter formatter = new BinaryFormatter();
                scene = (Scene)formatter.Deserialize(fs);
            }
        }
    }
}
