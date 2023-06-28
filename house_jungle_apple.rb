class Picture
  def initialize(name, year, type, artist)
    @name = name
    @year = year
    @type = type
    @artist = artist
  end
  
  attr_reader :name, :year, :type, :artist
  
  def print
    puts "#{@name} was a #{@type} created in #{@year} by #{@artist}"
  end
end

class Art
  @@all = []
  attr_reader :picture
 
  def initialize(picture)
    @picture = picture
    @@all << self
  end
 
  def self.all
    @@all
  end
 
  def self.find_by_artist(artist)
    all.select {|art| art.picture.artist == artist}
  end
 
end

markers = Picture.new("Markers", 2014, "Painting", "Karl")
lacy = Picture.new("Lacy", 2018, "Sculpture", "Frida")

Art.new(markers)
Art.new(lacy)

puts Art.find_by_artist("Karl").first.picture.print