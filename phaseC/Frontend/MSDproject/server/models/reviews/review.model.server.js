module.exports = function () {
    var mongoose = require("mongoose");
    var ReviewSchema = require("./review.schema.server.js")();
    var Review = mongoose.model("Review", ReviewSchema);

    var api = {
        findReview: findReview,
        findAllReviewsByUserId: findAllReviewsByUserId,
        findAllReviewsByMovieId : findAllReviewsByMovieId,
        findAllReviewsByMovieIdTwo : findAllReviewsByMovieIdTwo,
        updateReview: updateReview,
        addReview: addReview,
        deleteReview: deleteReview,
        getReviewByUserId: getReviewByUserId,
        getAllReviews: getAllReviews,
        deleteReviewByUserId : deleteReviewByUserId
    };
    return api;

    function deleteReviewByUserId(userId) {
        return Review.remove({"_user" : userId});
    }

    function getAllReviews() {
        return Review.find()
            .populate('_movie', '_id name imdbId' )
            .populate('_user', '_id username');
    }

    function getReviewByUserId(userId, movieId) {
        return Review.findOne({_user: userId, _movie: movieId});
    }

    function findReview(userId, rid) {
        return Review.findOne({_user: userId, _id: rid});
    }

    function findAllReviewsByUserId(userId) {
        return Review.find({_user: userId})
            .populate('_movie', '_id name imdbId' );
    }

    function updateReview(rid, review) {
        return Review
            .update({_id: rid}, {
                $set: {
                    reviewText:review
                }
            });
    }



    function findAllReviewsByMovieId(movieId) {
        return Review.find({_movie: movieId})
            .populate('_user', '_id username' );
    }

    function findAllReviewsByMovieIdTwo(movieId) {
        return Review.find({imdbId: movieId})
            .populate('_user', '_id username' );
    }

    function addReview(review) {
        return Review.create(review);
    }

    function deleteReview(rid) {
        return Review.remove({_id: rid});
    }

};

   /* var api={
        createReview:createReview,
        findAllReviewsForMovie:findAllReviewsForMovie,
        findReviewById:findReviewById,
        updateReview:updateReview,
        deleteReview:deleteReview
    };
    return api;

    function createReview(review) {
        console.log('in mongodb for review user');

        var x = Review.create(review);
        console.log(x.title);
        return x;
    }
    function findAllReviewsForMovie(movieId){
        return Review.find({_movie:movieId});
    }

    function findReviewById(rid){
        return Review.findById(rid);
    }
    function updateReview(review,rid){
        return Review.update({_id:rid},{
            $set:{
                title:review.title,
                description:review.description
            }
        })
    }
    function deleteReview(rid){
        return Review.remove({_id:rid});
    }

};*/