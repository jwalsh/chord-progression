(ns chord-progression.parser
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(defn parse-chord-progression
  "Parses a chord progression from a JSON file and returns a vector of vectors
  representing the chords for each section."
  [filename]
  (let [json-string (slurp (io/resource filename))
        chord-progression (json/read-str json-string)
        chord-list (map #(map keyword %) chord-progression)]
    chord-list))

