/*
 * GÉANT BSD Software License
 *
 * Copyright (c) 2017 - 2020, GÉANT
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the GÉANT nor the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * Disclaimer:
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.geant.idpextension.oidc.encoding.impl;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import net.shibboleth.utilities.java.support.net.HttpServletSupport;
import org.opensaml.messaging.context.MessageContext;
import org.opensaml.messaging.encoder.MessageEncodingException;
import org.opensaml.messaging.encoder.servlet.AbstractHttpServletResponseMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.oauth2.sdk.AuthorizationResponse;

/**
 * OIDC Authentication Response Decoder.
 */
public class OIDCAuthenticationResponseEncoder extends
        AbstractHttpServletResponseMessageEncoder<AuthenticationResponse> {

    /** Class logger. */
    private final Logger log = LoggerFactory.getLogger(OIDCAuthenticationResponseEncoder.class);

    /** {@inheritDoc} */
    protected void doEncode() throws MessageEncodingException {

        MessageContext<AuthenticationResponse> messageContext = getMessageContext();
        AuthenticationResponse resp = messageContext.getMessage();
        String redirect = ((AuthorizationResponse) resp).toURI().toString();

        HttpServletResponse response = getHttpServletResponse();
        HttpServletSupport.addNoCacheHeaders(response);
        HttpServletSupport.setUTF8Encoding(response);

        try {
            log.debug("Redirect set to {}", redirect);
            response.sendRedirect(redirect);
        } catch (IOException e) {
            throw new MessageEncodingException("Problem sending HTTP redirect", e);
        }
    }

}