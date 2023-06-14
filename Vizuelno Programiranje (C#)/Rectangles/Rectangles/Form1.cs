using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Rectangles
{
    public partial class Form1 : Form
    {
        public bool MouseDown { get; set; } = false;
        public Scene scene;
        public Point startingPoint { get; set; }
        public Form1()
        {
            InitializeComponent();
            scene= new Scene();
            DoubleBuffered = true;
        }

        private void toolStripStatusLabel1_Click(object sender, EventArgs e)
        {

        }

        private void Form1_MouseDown(object sender, MouseEventArgs e)
        {
            MouseDown= true;
            startingPoint = e.Location;
        }

        private void Form1_MouseUp(object sender, MouseEventArgs e)
        {
            MouseDown= false;
            scene.AddRectangle(new Rectangle())
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }
    }
}
