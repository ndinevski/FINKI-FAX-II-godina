namespace Airport
{
    partial class fNewAirport
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
            this.components = new System.ComponentModel.Container();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOK = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.tbLabelAirport = new System.Windows.Forms.TextBox();
            this.tbNameAirport = new System.Windows.Forms.TextBox();
            this.tbCityAirport = new System.Windows.Forms.TextBox();
            this.epCity = new System.Windows.Forms.ErrorProvider(this.components);
            this.epName = new System.Windows.Forms.ErrorProvider(this.components);
            this.epLabel = new System.Windows.Forms.ErrorProvider(this.components);
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.epCity)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.epName)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.epLabel)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btnCancel);
            this.groupBox1.Controls.Add(this.btnOK);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.tbLabelAirport);
            this.groupBox1.Controls.Add(this.tbNameAirport);
            this.groupBox1.Controls.Add(this.tbCityAirport);
            this.groupBox1.Location = new System.Drawing.Point(13, 13);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(394, 217);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Add Airport";
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(207, 188);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(171, 23);
            this.btnCancel.TabIndex = 7;
            this.btnCancel.Text = "Cancel";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // btnOK
            // 
            this.btnOK.Location = new System.Drawing.Point(18, 188);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(171, 23);
            this.btnOK.TabIndex = 6;
            this.btnOK.Text = "Save";
            this.btnOK.UseVisualStyleBackColor = true;
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(18, 133);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(36, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Label:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(18, 83);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(38, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "Name:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(18, 31);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(27, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "City:";
            // 
            // tbLabelAirport
            // 
            this.tbLabelAirport.Location = new System.Drawing.Point(18, 149);
            this.tbLabelAirport.Name = "tbLabelAirport";
            this.tbLabelAirport.Size = new System.Drawing.Size(80, 20);
            this.tbLabelAirport.TabIndex = 2;
            this.tbLabelAirport.Validating += new System.ComponentModel.CancelEventHandler(this.tbLabelAirport_Validating);
            // 
            // tbNameAirport
            // 
            this.tbNameAirport.Location = new System.Drawing.Point(18, 99);
            this.tbNameAirport.Name = "tbNameAirport";
            this.tbNameAirport.Size = new System.Drawing.Size(337, 20);
            this.tbNameAirport.TabIndex = 1;
            this.tbNameAirport.Validating += new System.ComponentModel.CancelEventHandler(this.tbNameAirport_Validating);
            // 
            // tbCityAirport
            // 
            this.tbCityAirport.Location = new System.Drawing.Point(18, 50);
            this.tbCityAirport.Name = "tbCityAirport";
            this.tbCityAirport.Size = new System.Drawing.Size(337, 20);
            this.tbCityAirport.TabIndex = 0;
            this.tbCityAirport.Validating += new System.ComponentModel.CancelEventHandler(this.tbCityAirport_Validating);
            // 
            // epCity
            // 
            this.epCity.BlinkRate = 1;
            this.epCity.ContainerControl = this;
            // 
            // epName
            // 
            this.epName.BlinkRate = 1;
            this.epName.ContainerControl = this;
            // 
            // epLabel
            // 
            this.epLabel.ContainerControl = this;
            // 
            // fNewAirport
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(419, 242);
            this.Controls.Add(this.groupBox1);
            this.Name = "fNewAirport";
            this.Text = "New Airport";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.epCity)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.epName)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.epLabel)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnOK;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox tbLabelAirport;
        private System.Windows.Forms.TextBox tbNameAirport;
        private System.Windows.Forms.TextBox tbCityAirport;
        private System.Windows.Forms.ErrorProvider epCity;
        private System.Windows.Forms.ErrorProvider epName;
        private System.Windows.Forms.ErrorProvider epLabel;
    }
}