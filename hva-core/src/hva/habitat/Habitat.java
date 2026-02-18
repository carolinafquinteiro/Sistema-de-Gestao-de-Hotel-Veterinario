package hva.habitat;

    import java.io.Serializable;
    import java.util.Map;
    
    import java.util.TreeMap;
    

    import hva.tree.Tree;
    import hva.animal.Animal;
    import hva.species.Species;


    
    /**
     * Represents a habitat in the hotel application
     */
    public class Habitat implements Serializable {
        // Habitat attributes
        private String id;
        private String name;
        private int area;
        private TreeMap<String, Tree> trees;
        private TreeMap<String, Animal> animals;
        private TreeMap<String, Species> species;

    
        /**
        * Constructor to initialize the attributes of the Habitat.
        *
        * @param id    the identifier of the habitat
        * @param name  the name of the habitat
        * @param area  the area of the habitat in square units
        */
        public Habitat(String id, String name, int area){
            this.id = id;
            this.name = name;
            this.area = area;
            this.trees = new TreeMap<>();
            this.animals = new TreeMap<>();
            this.species = new TreeMap<>();
        }

        /**
        * Getters to return the habitat's data
        *
        * @return the unique identifier, name, and area of the habitat
        */
        public String getId(){
            return id;
        }
        public TreeMap<String, Tree> getTrees(){
            return trees;
        }
        public TreeMap<String, Animal> getHabitatAnimals(){
            return animals;
        }
        public String getName(){
            return name;
        }
    
        public int getArea(){
            return area;
        }
    
        /**
        * Setters to modify the habitat's data
        *
        * @param id the new unique identifier for the habitat
        * @param area the new area of the habitat in square units
        */
        public void setHabitatId(String id) {
            this.id = id;
        }
    
        public void setHabitatArea(int area) {
            this.area = area;
        }

        /**
        * Adds an animal to the habitat
        *
        * @param animal the Animal object to add.
        */
        public void addAnimal(Animal animal) {
            animals.put(animal.getId().toUpperCase(), animal);
        }

        /**
        * Removes an animal from the habitat by its ID
        *
        * @param animalId the ID of the animal to remove.
        */
        public void removeAnimal(String animalId) {
            animals.remove(animalId.toUpperCase());
        }

        /**
        * Adds a tree to the habitat
        *
        * @param tree the Tree object to add.
        */
        public void addTree(Tree tree) {
            trees.put(tree.getId().toUpperCase(), tree);
        }

        /**
        * Adds a species to the habitat.
        *
        * @param id the species ID.
        * @param s the Species object to add.
        */
        public void addSpecie(String id, Species s){
            species.put(id, s);
        }

        /**
        * Returns the total population of animals in the habitat.
        *
        * @return the number of animals in the habitat.
        */
        public int totalpopulation(){
            return animals.size();
        }

        /**
        * Returns the number of trees in the habitat.
        *
        * @return the number of trees in the habitat.
        */
        public int numberofTrees(){
            return trees.size();
        }

        /**
         * Counts how many animals in the habitat belong to a specific species.
         *
         * @param specieid the species ID to count.
         * @return the number of animals of the specified species.
         */
        public int equalanimals(String specieid){
            int equals = 0;
            for (Animal a : animals.values()){
                if (a.getSpeciesId().equals(specieid)) {
                    equals++;
                }
            }
            return equals;
        }

        /**
         * Counts how many animals in the habitat are from different species than the one provided.
         *
         * @param specieid the species ID to exclude.
         * @return the number of animals not of the specified species.
         */
        public int diferentanimal(String specieid){
            int n = totalpopulation();
            int i = equalanimals(specieid);
            return n - i; 
        }

        /**
         * Calculates the adequacy score of the habitat for a given species based on its influence
         *
         * @param s the Species object to evaluate.
         * @return 0 if neutral, 20 if positive, -20 if negative.
         */
        public int getadequacy (Species s){
            String influence = s.getInfluence(id);

            if (influence.equals("NEU")){return 0; }
            if (influence.equals("POS")){return 20; }
            if (influence.equals("NEG")){return -20; }
            else { return 0;}
        }

        /**
         * Calculates the total cleaning difficulty for the habitat based on the cleaning difficulty of its trees.
         *
         * @return the total cleaning difficulty.
         */
        public int totalcleaning(){
            int total = 0;
            for (Tree t : trees.values()){
                total += t.calculateCleaningEffort();
            }
            return total;
        }
         
        /**
         * Calculates the total work needed for maintaining the habitat.
         * This is based on the habitat's area, the number of animals, and the total cleaning difficulty.
         *
         * @return the total work required for habitat maintenance.
         */
        public int workonhabitat(){
            return getArea() + 3*totalpopulation() + totalcleaning();
        }
    }
