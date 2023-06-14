using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Rectangles
{
    public class Rectangle
    {
        public int width { get; set; }
        public int height { get; set; }
        public bool isSquare { get; set; }
        public Color Color { get; set; } = Color.Blue;
        public Point Center {  get; set; }

        public Rectangle(Point center, int width, int height, Color color, bool isSquare)
        {
            Center = center;
            this.width = width;
            this.height = height;
            Color = color;
            this.isSquare = isSquare;
        }

        public void Draw(Graphics g)
        {
            Brush b = new SolidBrush(Color);
            g.FillRectangle(b, Center.X - width/2, Center.Y- height/2, width, height);
            b.Dispose();
        }


    }
}
