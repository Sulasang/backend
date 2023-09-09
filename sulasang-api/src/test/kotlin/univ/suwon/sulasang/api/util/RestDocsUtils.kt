package univ.suwon.sulasang.api.util

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors

object RestDocsUtils {

    fun getDocumentRequest(): OperationRequestPreprocessor {
        return Preprocessors.preprocessRequest(
            Preprocessors.modifyUris()
                .scheme("http")
                .host("3.38.65.72")
                .port(8080),
            Preprocessors.prettyPrint()
        )
    }

    fun getDocumentResponse(): OperationResponsePreprocessor {
        return Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
    }
}
