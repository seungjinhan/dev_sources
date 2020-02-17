
/**
 * Array에 저장된 JSON데이터에서  searchWord가 포함되어 있는 json을 조회
 */
module.exports.searchJSonArray = function( arr, searchWord){

    return arr.filter( function( d){

        const keys = Object.keys( d)
        for (let i = 0; i < keys.length; i++) {

            key = keys[i]
            if( (""+d[key]).includes( searchWord) ){
                
                return d
            }
        }
    })
}