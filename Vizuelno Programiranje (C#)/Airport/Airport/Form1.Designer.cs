namespace Airport
{
    partial class fAirport
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lbAirports = new System.Windows.Forms.ListBox();
            this.lbDestinations = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.btnAddAirport = new System.Windows.Forms.Button();
            this.btnRmAirport = new System.Windows.Forms.Button();
            this.btnAddDest = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.tbMostExpensiveDest = new System.Windows.Forms.TextBox();
            this.tbAverageLength = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // lbAirports
            // 
            this.lbAirports.FormattingEnabled = true;
            this.lbAirports.Location = new System.Drawing.Point(30, 57);
            this.lbAirports.Name = "lbAirports";
            this.lbAirports.Size = new System.Drawing.Size(267, 264);
            this.lbAirports.TabIndex = 0;
            this.lbAirports.SelectedIndexChanged += new System.EventHandler(this.lbAirports_SelectedIndexChanged);
            // 
            // lbDestinations
            // 
            this.lbDestinations.FormattingEnabled = true;
            this.lbDestinations.Location = new System.Drawing.Point(342, 57);
            this.lbDestinations.Name = "lbDestinations";
            this.lbDestinations.Size = new System.Drawing.Size(408, 264);
            this.lbDestinations.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(27, 38);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(45, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Airports:";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(339, 38);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(68, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Destinations:";
            // 
            // btnAddAirport
            // 
            this.btnAddAirport.Location = new System.Drawing.Point(30, 362);
            this.btnAddAirport.Name = "btnAddAirport";
            this.btnAddAirport.Size = new System.Drawing.Size(267, 23);
            this.btnAddAirport.TabIndex = 4;
            this.btnAddAirport.Text = "Add airport";
            this.btnAddAirport.UseVisualStyleBackColor = true;
            this.btnAddAirport.Click += new System.EventHandler(this.btnAddAirport_Click);
            // 
            // btnRmAirport
            // 
            this.btnRmAirport.Location = new System.Drawing.Point(30, 418);
            this.btnRmAirport.Name = "btnRmAirport";
            this.btnRmAirport.Size = new System.Drawing.Size(267, 23);
            this.btnRmAirport.TabIndex = 5;
            this.btnRmAirport.Text = "Remove airport";
            this.btnRmAirport.UseVisualStyleBackColor = true;
            this.btnRmAirport.Click += new System.EventHandler(this.btnRmAirport_Click);
            // 
            // btnAddDest
            // 
            this.btnAddDest.Location = new System.Drawing.Point(30, 475);
            this.btnAddDest.Name = "btnAddDest";
            this.btnAddDest.Size = new System.Drawing.Size(267, 23);
            this.btnAddDest.TabIndex = 6;
            this.btnAddDest.Text = "Add destination";
            this.btnAddDest.UseVisualStyleBackColor = true;
            this.btnAddDest.Click += new System.EventHandler(this.btnAddDest_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.tbAverageLength);
            this.groupBox1.Controls.Add(this.tbMostExpensiveDest);
            this.groupBox1.Location = new System.Drawing.Point(342, 362);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(408, 136);
            this.groupBox1.TabIndex = 7;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Destinations";
            // 
            // tbMostExpensiveDest
            // 
            this.tbMostExpensiveDest.Location = new System.Drawing.Point(6, 44);
            this.tbMostExpensiveDest.Name = "tbMostExpensiveDest";
            this.tbMostExpensiveDest.ReadOnly = true;
            this.tbMostExpensiveDest.Size = new System.Drawing.Size(396, 20);
            this.tbMostExpensiveDest.TabIndex = 0;
            this.tbMostExpensiveDest.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // tbAverageLength
            // 
            this.tbAverageLength.Location = new System.Drawing.Point(6, 91);
            this.tbAverageLength.Name = "tbAverageLength";
            this.tbAverageLength.ReadOnly = true;
            this.tbAverageLength.Size = new System.Drawing.Size(396, 20);
            this.tbAverageLength.TabIndex = 1;
            this.tbAverageLength.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 25);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(135, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Most expensive destination";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(6, 75);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(133, 13);
            this.label4.TabIndex = 3;
            this.label4.Text = "Average destination length";
            // 
            // fAirport
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 554);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.btnAddDest);
            this.Controls.Add(this.btnRmAirport);
            this.Controls.Add(this.btnAddAirport);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lbDestinations);
            this.Controls.Add(this.lbAirports);
            this.Name = "fAirport";
            this.Text = "Airport";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox lbAirports;
        private System.Windows.Forms.ListBox lbDestinations;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button btnAddAirport;
        private System.Windows.Forms.Button btnRmAirport;
        private System.Windows.Forms.Button btnAddDest;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tbAverageLength;
        private System.Windows.Forms.TextBox tbMostExpensiveDest;
    }
}

