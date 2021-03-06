(ns pepa.resources
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

;;; Provides a macro which lists all files under
;;; resources/public/img/. This is used by ClojureScript to fire up an
;;; image-preloader

(def +public-path+ "resources/public/")
(def +image-path+ (str +public-path+ "img/"))

(defmacro image-resources []
  (let [files# (->> (io/file +image-path+)
                   (file-seq)
                   (remove #(.isDirectory %))
                   (map str)
                   (map #(s/replace-first % +public-path+ "")))]
    (vec files#)))
