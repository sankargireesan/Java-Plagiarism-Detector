module.exports = function (app, models) {

    var favoriteModel = models.favoriteModel;
    var movieModel = models.movieModel;

    app.post("/api/project/movie/user/viewed", addMovie)
    app.post("/api/user/:userId/favorite", favoriteMovie);
    app.delete("/api/unFavorite/user/movie/:userId", deleteFavorite);
    app.get("/api/user/checkLike/:userId/movie/:movieId", findThisFavoriteByUserId);
    app.delete("/api/user/:userId/removeFavorite/:movieId", unFavoriteMovie);
    app.get("/api/user/fetchFavoriteMovie/:userId", findAllFavoriteByUserId);
    app.get("/api/user/movie/:movieId", findAllFavoriteByMovieId);
    app.get("/api/user/movie/:movieId/imdbId", findMovie);

    function findMovie(req, res) {
        console.log("testing 123");
        movieModel
            .findMovie(req.params.movieId)
            .then(
                function (mdata) {
                    res.json(mdata);
                },
                function (error) {
                    res.status(400).send(error);
                }
            );
    }

    function addMovie(req, res) {
        movieModel
            .findMovie(req.body.imdbId)
            .then(
                function (mdata, error) {
                    if (mdata == null) {
                        movieModel
                            .addMovie(req.body)
                            .then(
                                function (amdata) {
                                    res.json(amdata);
                                }, function (error) {
                                    res.statusCode(400).send(error);
                                })
                    } else {
                        res.json(mdata);
                    }
                }, function (error) {
                    res.statusCode(400).send(error);
                });
    }


    function favoriteMovie(req,res) {
        var userId = req.params.userId;
        var movie = req.body;
        var movieId = movie.imdbId;
        var actualmovieid = "";

        var tempmovie = {
            imdbId: movie.imdbID,
            name: movie.Title,
            poster: movie.Poster,
            director: movie.Director,
            released: movie.Released,
            plot: movie.Plot,
            actors: movie.Actors,
            runtime: movie.Runtime,
            genre: movie.Genre,
            country: movie.Country,
            language: movie.Language,
            imdbrating: movie.Imdbrating,
            awards: movie.Awards
        }
console.log(tempmovie);


        movieModel
            .findMovie(movieId)
            .then(
                function (mdata, error) {
                    if (mdata == null) {
                        movieModel
                            .addMovie(tempmovie)
                            .then(
                                function (odata) {
                                    actualmovieid = odata._id;
                                    console.log(actualmovieid);
                                    var fav = {
                                        _user: userId,
                                        _movie: actualmovieid,
                                        moviename:movie.Title
                                    };

                                    favoriteModel
                                        .findFavorite(userId, actualmovieid)
                                        .then(
                                            function (obfav) {
                                                if (obfav == null) {
                                                    favoriteModel
                                                        .addFavorite(fav)
                                                        .then(
                                                            function (favob) {
                                                                res.json(favob);
                                                            },
                                                            function (err) {
                                                                res.status(400).send(err);
                                                            }
                                                        );

                                                } else {
                                                    res.json(obfav);
                                                }
                                            }, function (error) {
                                                res.status(400).send(error);
                                            });


                                    res.json(odata);
                                },
                                function (err) {
                                    res.status(400).send(err);
                                }
                            );

                    } else {
                        actualmovieid = mdata._id;
                        console.log(actualmovieid);
                        var fav = {
                            _user: userId,
                            _movie: actualmovieid,
                            moviename:movie.Title
                        };
                        favoriteModel
                            .findFavorite(userId, actualmovieid)
                            .then(
                                function (obfav, error) {
                                    if (obfav == null) {
                                        favoriteModel
                                            .addFavorite(fav)
                                            .then(
                                                function (favob) {
                                                    res.json(favob);
                                                },
                                                function (err) {
                                                    res.status(400).send(err);
                                                }
                                            );

                                    } else {
                                        res.json(obfav);
                                    }
                                });
                        res.json(mdata);
                    }
                });

    }

    function findAllFavoriteByUserId(req, res) {
        favoriteModel
            .findAllFavoriteByUserId(req.params.userId)
            .then(
                function (movies) {
                    res.json(movies);
                },
                function (error) {
                    res.status(400).send(error);
                }
            )
    }

    function deleteFavorite(req, res) {
        favoriteModel
            .deleteFavorite(req.params.userId)
            .then(
                function (stats) {
                    console.log(stats);
                    res.send(200);
                },
                function (error) {
                    res.statusCode(404).send(error);
                }
            );
    }


    function findAllFavoriteByMovieId(req, res) {
        console.log(req.params.movieId);
        likeModel
            .findAllFavoriteByMovieId(req.params.movieId)
            .then(
                function (users) {

                    res.json(users);
                },
                function (error) {
                    res.status(400).send(error);
                }
            )
    }

    function findThisFavoriteByUserId(req, res) {
        favoriteModel
            .findFavorite(req.params.userId, req.params.movieId)
            .then(
                function (userdata) {
                    res.json(userdata);
                },
                function (error) {
                    res.status(400).send(error);
                }
            )
    }

    function unFavoriteMovie(req, res) {
        favoriteModel
            .unFavoriteMovie(req.params.userId, req.params.movieId)
            .then(
                function (stats) {
                    console.log(stats);
                    res.send(200);
                },
                function (error) {
                    res.statusCode(404).send(error);
                }
            );
    }








}