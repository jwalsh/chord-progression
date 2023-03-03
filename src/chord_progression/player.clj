(ns chord-progression.player
  (:require [overtone.live :refer :all]))

(defn play-chord
  "Plays a single chord given as a vector of notes. Each note should be a keyword
  representing the pitch (e.g. :a4)."
  [notes]
  (let [synth (synth
                (defsynth chord-synth [freq 440] 
                  (let [amp-env (env-gen (perc 0.01 0.5) :action FREE :gate (env-gen (trig 1) :dur 1))
                        freq-env (env-gen (lin-lin [0 1] [freq (* 2 freq)] :exponential) :time 0.01)
                        s (sin-osc freq-env)]
                    (* amp-env s))))
        freqs (map #(midi->hz (note %)) notes)]
    (apply synth (map #(hash-map :freq %) freqs))))


(defn play-section
  "Plays a section of a chord progression given as a vector of vectors of notes."
  [section]
  (let [dur 1]
    (doseq [notes section]
      (play-chord notes)
      (sleep dur))))


(defn play-chord-progression
  "Plays a chord progression given as a vector of vectors of notes."
  [chord-progression]
  (doseq [section chord-progression]
    (play-section section)
    (sleep 1)))
