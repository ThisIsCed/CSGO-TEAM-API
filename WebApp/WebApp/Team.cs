namespace WebApp
{
    public class Team
    {
        public string Id { get; }
        public string Name { get; set; }
        public string Location { get; set; }
        public string Region { get; set; }
        public string[] Founders { get; set; }
        public string[] Players { get; set; }
        public string Url { get; set; }

        public Team(string id, string name, string location, string region, string[] founders, string[] players, string url)
        {
            this.Id = id;
            this.Name = name;
            this.Location = location;
            this.Region = region;
            this.Founders = founders;
            this.Players = players;
            this.Url = url;
        }
    }
}