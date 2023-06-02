using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Paint
{
    public class Line
    {
        public Point Begin { get; set; }
        public Point End { get; set; }
        public int Thickness { get; set; } = 1;
        public Color color { get; set; } = Color.Black;

        public Line(Point b, Point e, int t, Color c) {
            this.Begin = b;
            this.End = e;
            this.Thickness = t;
            this.color = c;
        }

        public void Draw(Graphics g)
        {
            Pen pen = new Pen(color, Thickness);
            g.DrawLine(pen, Begin, End);
            pen.Dispose();
        }


    }
}
