<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0" xmlns:cmd="http://www.clarin.eu/cmd/1" xmlns:cmdp="http://www.clarin.eu/cmd/1/profiles/clarin.eu:cr1:p_1381926654438" xmlns:tei="http://www.tei-c.org/ns/1.0" exclude-result-prefixes="tei">
  
  <!-- 
    ****************************************************************************************
    Stylesheet for the conversion from DTABf-teiHeader to DTABf-CMDI profile
    Published by: CLARIN Service Center at the Berlin-Brandenburg Academy of Sciences 
        and the Humantities (BBAW), http://clarin.bbaw.de
    Author: Susanne Haaf (BBAW)
    Licence: Distributed under the Creative Commons Attribution-ShareAlike 3.0 Germany 
        (CC BY-SA 3.0 DE) Licence: https://creativecommons.org/licenses/by-sa/3.0/de/deed.en
    *****************************************************************************************
  -->
  
  <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
  <xsl:strip-space elements="*"/>
    
  <xsl:template match="/">
    <xsl:variable name="dirName" select="//tei:idno[@type = 'DTADirName']"/>
    <cmd:CMD xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://www.clarin.eu/cmd/1 http://kaskade.dwds.de/~haaf/dtabf/schema/cmdi_teiHeader.xsd"
 xmlns:cmd="http://www.clarin.eu/cmd/1" xmlns:cmdp="http://www.clarin.eu/cmd/1/profiles/clarin.eu:cr1:p_1381926654438" CMDVersion="1.2" >
      <cmd:Header>
        <cmd:MdCreator>Deutsches Textarchiv</cmd:MdCreator>
        <cmd:MdCreationDate><xsl:value-of select="current-date()"/></cmd:MdCreationDate>
        <cmd:MdSelfLink>http://www.deutschestextarchiv.de/api/cmdi/<xsl:value-of select="substring-after(//tei:idno[@type = 'URLWeb'], 'www.deutschestextarchiv.de/')"/></cmd:MdSelfLink>
        <cmd:MdProfile>clarin.eu:cr1:p_1381926654438</cmd:MdProfile>
        <cmd:MdCollectionDisplayName>Deutsches Textarchiv (1600–1900)</cmd:MdCollectionDisplayName>
      </cmd:Header>
      <cmd:Resources>
        <cmd:ResourceProxyList>
          <cmd:ResourceProxy>
            <xsl:attribute name="id">dta-<xsl:value-of select="$dirName"/>.xml</xsl:attribute>
            <cmd:ResourceType mimetype="application/xml">Resource</cmd:ResourceType>
            <cmd:ResourceRef><xsl:value-of select="//tei:idno[@type = 'URLXML']"/></cmd:ResourceRef>
          </cmd:ResourceProxy>
          <cmd:ResourceProxy>
            <xsl:attribute name="id">dta-<xsl:value-of select="$dirName"/>.xhtml</xsl:attribute>
            <cmd:ResourceType mimetype="application/xhtml+xml">Resource</cmd:ResourceType>
            <cmd:ResourceRef><xsl:value-of select="//tei:idno[@type = 'URLHTML']"/></cmd:ResourceRef>
          </cmd:ResourceProxy>
          <cmd:ResourceProxy>
            <xsl:attribute name="id">dta-<xsl:value-of select="$dirName"/>.text</xsl:attribute>
            <cmd:ResourceType mimetype="text/plain">Resource</cmd:ResourceType>
            <cmd:ResourceRef><xsl:value-of select="//tei:idno[@type = 'URLText']"/></cmd:ResourceRef>
          </cmd:ResourceProxy>
          <cmd:ResourceProxy>
            <xsl:attribute name="id">dta-<xsl:value-of select="$dirName"/>.landing_page</xsl:attribute>
            <cmd:ResourceType>LandingPage</cmd:ResourceType>
            <cmd:ResourceRef><xsl:value-of select="//tei:idno[@type = 'URLWeb']"/></cmd:ResourceRef>
          </cmd:ResourceProxy>
          <cmd:ResourceProxy>
            <xsl:attribute name="id">dta-<xsl:value-of select="$dirName"/>.search</xsl:attribute>
            <cmd:ResourceType mimetype="application/sru+xml">SearchService</cmd:ResourceType>
            <cmd:ResourceRef>http://dspin.dwds.de:8088/ddc-sru/dta/</cmd:ResourceRef>
          </cmd:ResourceProxy>
        </cmd:ResourceProxyList>
        <cmd:JournalFileProxyList/>
        <cmd:ResourceRelationList/>
      </cmd:Resources>
      <cmd:Components>
        <xsl:apply-templates select="//tei:teiHeader"/>
      </cmd:Components>
    </cmd:CMD>
  </xsl:template>

  <xsl:template match="//tei:teiHeader">
      <cmdp:teiHeader>
      <xsl:apply-templates/>
    </cmdp:teiHeader>
  </xsl:template>

  <xsl:template match="//tei:publicationStmt">
    <cmdp:publicationStmt xmlns:cmdp="http://www.clarin.eu/cmd/1/profiles/clarin.eu:cr1:p_1381926654438">
        <xsl:for-each select="child::tei:pubPlace">
            <xsl:element name="cmdp:{local-name()}">
            <xsl:apply-templates/></xsl:element>
        </xsl:for-each>
        <xsl:for-each select="child::tei:date">
            <xsl:element name="cmdp:{local-name()}">
                <xsl:for-each select="@*">
                    <xsl:attribute name="{local-name()}"><xsl:value-of select="."/></xsl:attribute>
                </xsl:for-each>
                <xsl:apply-templates/>
            </xsl:element>
        </xsl:for-each>
      <xsl:apply-templates/>
    </cmdp:publicationStmt>
  </xsl:template>

    
  <xsl:template match="//tei:tagsDecl"/>

  <xsl:template match="//tei:pubPlace"/>

  <xsl:template match="//tei:date"/>

  <xsl:template match="//tei:publisher">
    <!-- eliminate attributes in <publisher>-element -->
    <cmdp:publisher>
      <xsl:apply-templates/>
    </cmdp:publisher>
  </xsl:template>

  <xsl:template match="//tei:idno[not(child::tei:idno or parent::tei:idno)][position() = 1]">
    <cmdp:idno>
      <xsl:copy-of select="."/>
      <xsl:for-each select="following-sibling::tei:idno">
        <xsl:copy-of select="self::*"/>
      </xsl:for-each>
    </cmdp:idno>
  </xsl:template>

  <xsl:template match="//tei:idno[not(child::tei:idno or parent::tei:idno)][position() > 1]"/>

  <xsl:template match="//tei:editor" xmlns="http://www.tei-c.org/ns/1.0">
    <!-- eliminate attributes in <editor>-element -->
    <cmdp:editor>
      <xsl:apply-templates/>
    </cmdp:editor>
  </xsl:template>

  <xsl:template match="//tei:persName">
    <xsl:variable name="pnd" select="./@ref"/>
    <cmdp:persName>
        <xsl:for-each select="child::*">
            <xsl:element name="cmdp:{local-name()}">
                <xsl:apply-templates/>
            </xsl:element>
        </xsl:for-each>
      <xsl:if test="$pnd != ''">
        <cmdp:idno>
          <cmdp:idno type="PND">
            <xsl:value-of select="$pnd"/>
          </cmdp:idno>
        </cmdp:idno>
      </xsl:if>
    </cmdp:persName>
  </xsl:template>

  <xsl:template match="//tei:orgName[1]" >
    <cmdp:orgName>
      <xsl:element name="cmdp:orgName">
        <xsl:if test="./@role">
          <xsl:attribute name="role">
            <xsl:value-of select="./@role"/>
          </xsl:attribute>
        </xsl:if>
        <xsl:if test="./@xml:lang">
          <xsl:attribute name="xml:lang">
            <xsl:value-of select="./@xml:lang"/>
          </xsl:attribute>
        </xsl:if>
        <xsl:value-of select="."/>
      </xsl:element>
      <xsl:for-each select="following-sibling::tei:orgName" >
        <xsl:element name="cmdp:orgName">
          <xsl:if test="./@role">
            <xsl:attribute name="role">
              <xsl:value-of select="./@role"/>
            </xsl:attribute>
          </xsl:if>
          <xsl:if test="./@xml:lang">
            <xsl:attribute name="xml:lang">
              <xsl:value-of select="./@xml:lang"/>
            </xsl:attribute>
          </xsl:if>
          <xsl:value-of select="self::*"/>
        </xsl:element>
      </xsl:for-each>
    </cmdp:orgName>
  </xsl:template>

  <xsl:template match="//tei:orgName[position() > 1]"/>
  <xsl:template match="//tei:ref[parent::tei:*/child::text()[matches(., '\S')]]">
      <xsl:value-of select="."/>
  </xsl:template>
    
    <xsl:template match="@*">
      <xsl:attribute name="{local-name()}">
         <xsl:value-of select="."/>
      </xsl:attribute>
   </xsl:template>

   <xsl:template match="tei:*">
       <xsl:element name="cmdp:{local-name()}">
         <xsl:apply-templates select="@* | node()"/>
      </xsl:element>
   </xsl:template>
</xsl:stylesheet>
