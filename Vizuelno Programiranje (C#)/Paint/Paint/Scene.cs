using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Paint
{
    public class Scene
    {
        public List<Line> lines;
        public Point lastPoint;
        public int Thickness { get; set; } = 1;
        public Color color { get; set; } = Color.Black;
        public bool Pointer { get; set; } = false;
        public Point Cursor { get; set; } = new Point();
        public int Width { get; set; } = 0;
        public int Height { get; set; } = 0;
        public Stack<Line> UndoStack { get; set; } = new Stack<Line>();

        public Scene() { 
            lines= new List<Line>();
            lastPoint = Point.Empty;
        }

        public void AddLine(Point newPoint)
        {
            if (lastPoint == Point.Empty)
            {
                lastPoint = newPoint;
            }
            else
            {
                lines.Add(new Line(lastPoint, newPoint, Thickness, color));
            }
            if (lines.Count > 0)
            {
                lastPoint = lines[lines.Count - 1].End;
            }
        }


        public void Draw(Graphics g)
        {
            foreach(Line line in lines)
            {
                line.Draw(g);
            }

            if(Pointer)
            {
                Pen pen = new Pen(Color.Gray);
                pen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
                g.DrawLine(pen, new Point(Cursor.X, 0), new Point(Cursor.X, Height));
                g.DrawLine(pen, new Point(0, Cursor.Y), new Point(Width, Cursor.Y));
                pen.Dispose();
            }
        }

        internal void setThickness(int v)
        {
            Thickness = v;
        }

        internal void setColor(Color color)
        {
            this.color = color;
        }

        internal void Undo()
        {
            if (lines.Count > 0)
            {
                Line line = lines[lines.Count - 1];
                lines.Remove(line);
                lastPoint = line.End;
                UndoStack.Push(line);
            }
        }

        internal void Redo()
        {
            if (UndoStack.Count > 0)
            {
                Line line = UndoStack.Pop();
                lines.Add(line);
                lastPoint = line.End;
            }
        }
    }
}
