using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallsPulsing
{
    internal class Circle : Shape
    {
        public Point Center { get; set; }
        public float Radius { get; set; }
        public Color Color { get; set; } = Color.Red;
        public bool IssSelected { get; set; } = false;
        public bool IncreasingRadius { get; set; } = true;
        public int MaxRadius { get; set; } = 80;


        public Circle(Point center, float radius)
        {
            Center = center;
            Radius = radius;         
        }

        public override void Draw(Graphics g)
        {
            Brush brush = new SolidBrush(Color);
            g.FillEllipse(brush, Center.X - Radius, Center.Y - Radius, 2 * Radius, 2 * Radius);
            brush.Dispose();

            if (IssSelected)
            {
                Pen pen = new Pen(Color.Green, 3);
                g.DrawEllipse(pen, Center.X - Radius, Center.Y - Radius, 2 * Radius, 2 * Radius);
                pen.Dispose();
            }
        }

        public override bool isHit(Point p)
        {
            bool result = Math.Sqrt(Math.Pow(Center.X - p.X,2) + Math.Pow(Center.Y - p.Y,2)) < Radius;
            if (result)
            {
                IssSelected = !IssSelected;
            }
            return result;
        }

        public override void Pulse()
        {
            if (IncreasingRadius)
            {
                if (Radius < MaxRadius-5)
                {
                    Radius += 5;
                }
                else
                {
                    IncreasingRadius = false;
                }
            }
            else
            {
                if(Radius > 5)
                {
                    Radius -= 5;
                }
                else
                {
                    IncreasingRadius = true;
                }
            }
        }

        public override bool IsSelected()
        {
            return IssSelected;
        }

        public override void MoveRight()
        {
            Center = new Point(Center.X + 9, Center.Y);
        }
    }
}
