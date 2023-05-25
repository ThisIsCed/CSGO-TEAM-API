using System;

namespace Pos_Projekt
{
    internal class Teams
    {
        public string id { get; }
        public string name { get; set; }
        public string location { get; set; }
        public string region { get; set; }
        public string[] founders { get; set; }
        public string[] players { get; set; }
        public string url { get; set; }

        public Teams() 
        {
            this.id = Guid.NewGuid().ToString();
        }

        public Teams(string id,string name, string location, string region, string[] founders, string[] players, string url)
        {
            this.id = id;
            this.name = name;
            this.location = location;
            this.region = region;
            this.founders = founders;
            this.players = players;
            this.url = url;
        }
    }
}
