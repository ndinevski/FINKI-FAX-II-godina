using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Paint
{
    public partial class Form1 : Form
    {
        Scene scene;
        public Form1()
        {
            InitializeComponent();
            scene = new Scene();
            scene.Width = Width; 
            scene.Height = Height;
            statusLabel.Text = $"Lines: 0";
            DoubleBuffered = true;
            MinimizeBox= true;
            MaximizeBox = true;

        }

        private void toolStripStatusLabel1_Click(object sender, EventArgs e)
        {

        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            scene.AddLine(e.Location);
            UpdateCount();
            scene.UndoStack.Clear();
            Invalidate();
        }

        private void UpdateCount()
        {
            statusLabel.Text = $"Lines: {scene.lines.Count}";
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            scene.Draw(e.Graphics);
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene = new Scene();
        }

        private void thinToolStripMenuItem_Click(object sender, EventArgs e)
        {
            thinToolStripMenuItem.Checked = true;
            mediumToolStripMenuItem.Checked = false;
            hardToolStripMenuItem.Checked = false;

            scene.setThickness(1);
        }

        private void mediumToolStripMenuItem_Click(object sender, EventArgs e)
        {
            thinToolStripMenuItem.Checked = false;
            mediumToolStripMenuItem.Checked = true;
            hardToolStripMenuItem.Checked = false;

            scene.setThickness(3);
        }

        private void hardToolStripMenuItem_Click(object sender, EventArgs e)
        {
            thinToolStripMenuItem.Checked = false;
            mediumToolStripMenuItem.Checked = false;
            hardToolStripMenuItem.Checked = true;

            scene.setThickness(5);
        }

        private void colorToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ColorDialog dialog = new ColorDialog();
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                scene.setColor(dialog.Color);
            }
        }

        private void pointerToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene.Pointer = !scene.Pointer; 
            pointerToolStripMenuItem.Checked = !pointerToolStripMenuItem.Checked;
            Invalidate();
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            scene.Cursor = e.Location;
            Invalidate();
        }

        private void undoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene.Undo();
            UpdateCount();
            Invalidate();
        }

        private void redoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            scene.Redo();
            UpdateCount();
            Invalidate();
        }
    }
}
